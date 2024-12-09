package com.dicoding.instahelp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // Ganti localhost dengan IP komputer pengembangan
    private const val BASE_URL = "http://10.140.227.93:5000"  // Ganti dengan IP lokal komputer kamu

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Gunakan BASE_URL yang sudah didefinisikan
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

