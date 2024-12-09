package com.dicoding.instahelp.API

data class ResidentRegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
    val address: String?,
    val nik: String,
    val date_of_birth: String,  // Format: "yyyy-MM-dd"
    val place_of_birth: String,
    val gender: String,
    val phone_number: String,
    val password_confirmation: String
)
