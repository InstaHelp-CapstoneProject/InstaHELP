package com.dicoding.instahelp.API


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/register")
    fun loginUser (@Body user: ResidentAuth): Call<ResidentAuth>
}