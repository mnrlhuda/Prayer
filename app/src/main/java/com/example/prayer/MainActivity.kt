package com.example.prayer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prayer.adapter.DoaAdapter
import com.example.prayer.databinding.ActivityMainBinding
import com.example.prayer.model.Doa
import com.example.prayer.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var api: ApiService
    private lateinit var adapter: DoaAdapter
    private var originalDoaList: List<Doa> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRetrofit()
        getAllDoa()
        setupSearch()
    }

    private fun setupRetrofit() {
        val logging = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://doa-doa-api-ahmadramadhan.fly.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    private fun getAllDoa() {
        api.getAllDoa().enqueue(object : Callback<List<Doa>> {
            override fun onResponse(call: Call<List<Doa>>, response: Response<List<Doa>>) {
                if (response.isSuccessful) {
                    originalDoaList = response.body()!!
                    adapter = DoaAdapter(originalDoaList)
                    binding.rvDoa.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = this@MainActivity.adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Doa>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterDoaList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterDoaList(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalDoaList
        } else {
            originalDoaList.filter { doa ->
                doa.doa.contains(query, ignoreCase = true) ||
                        doa.latin.contains(query, ignoreCase = true) ||
                        doa.artinya.contains(query, ignoreCase = true)
            }
        }
        adapter.updateList(filteredList)
    }
}