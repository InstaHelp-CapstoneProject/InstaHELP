package com.dicoding.instahelp.API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    // Endpoint untuk login resident
    @POST("login/resident")
    fun loginResident(@Body loginRequest: LoginResidentRequest): Call<LoginResponse>

    // Endpoint untuk login institution
    @POST("http://127.0.0.1:5000/auth/login")
    fun loginInstitution(@Body loginRequest: LoginInstitutionRequest): Call<LoginResponse>

    // Endpoint untuk register resident
    @POST("http://127.0.0.1:5000/auth/register")
    fun registerResident(@Body registerRequest: ResidentRegistrationRequest): Call<RegisterResponse>

    // Endpoint untuk register institution
    @POST("register/institution")
    fun registerInstitution(@Body registerRequest: InstitutionRegistrationRequest): Call<RegisterResponse>

    // Endpoint untuk forgot password
    @POST("forgot-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Call<PasswordResponse>

}
