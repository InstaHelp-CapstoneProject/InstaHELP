package com.dicoding.instahelp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://127.0.0.1:5000"

    // Membuat instance Retrofit sekali saja
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Menggunakan retrofit yang sudah dibuat untuk apiService
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
