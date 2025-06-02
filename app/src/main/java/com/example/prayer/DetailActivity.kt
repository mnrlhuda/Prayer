package com.example.prayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prayer.databinding.ActivityDetailBinding
import com.example.prayer.model.Doa

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doa = intent.getSerializableExtra("doa") as Doa

        binding.tvJudul.text = doa.doa
        binding.tvArab.text = doa.ayat
        binding.tvLatin.text = doa.latin
        binding.tvTerjemahan.text = doa.artinya
    }
}
