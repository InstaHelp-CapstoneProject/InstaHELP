package com.dicoding.instahelp.API

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface InstitutionsService {

    @GET("institutions")
    fun getInstitutions(
        @Query("name") name: String? = null
    ): Call<List<Institutions>>

    @GET("institutions")
    fun searchInstitutions(
        @Query("name") name: String
    ): Call<List<Institutions>>

    companion object {
        private const val BASE_URL = "https://instahelp-api-825145996190.asia-southeast2.run.app"

        fun create(): InstitutionsService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(InstitutionsService::class.java)
        }
    }
}
