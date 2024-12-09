package com.dicoding.instahelp.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    // Endpoint untuk login resident
    @POST("auth/login")
    fun loginResident(@Body loginRequest: LoginResidentRequest): Call<LoginResponse>

    // Endpoint untuk login institution
    @POST("auth/login")
    fun loginInstitution(@Body loginRequest: LoginInstitutionRequest): Call<LoginResponse>

    // Endpoint untuk register resident
    @POST("auth/register")
    fun registerResident(@Body registerRequest: ResidentRegistrationRequest): Call<RegisterResponse>

    // Endpoint untuk register institution
    @POST("register/register")
    fun registerInstitution(@Body registerRequest: InstitutionRegistrationRequest): Call<RegisterResponse>

    // Endpoint untuk forgot password
    @POST("forgot-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Call<PasswordResponse>
}
