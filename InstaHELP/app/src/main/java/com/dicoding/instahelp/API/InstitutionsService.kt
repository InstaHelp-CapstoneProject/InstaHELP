package com.dicoding.instahelp.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InstitutionsService {
    @GET("institutions")
    fun getInstitutions(
        @Query("name") name: String?
    ): Call<List<Institutions>>
}
