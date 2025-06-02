package com.example.prayer.network

import com.example.prayer.model.Doa
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api")
    fun getAllDoa(): Call<List<Doa>>

    @GET("api/doa/{doa}")
    fun searchDoa(@Path("doa") doa: String): Call<List<Doa>>
}
