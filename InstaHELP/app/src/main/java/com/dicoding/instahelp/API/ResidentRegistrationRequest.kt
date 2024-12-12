package com.dicoding.instahelp.API

data class ResidentRegistrationRequest(
    val role: String,  // "resident"
    val name: String,  // Can be empty, but make sure to validate it properly
    val email: String,
    val address: String,
    val username: String?,  // Added username field
    val nik: String,
    val date_of_birth: String,  // Format: "yyyy-MM-dd"
    val place_of_birth: String,
    val gender: String,  // "MAN", "WOMAN" or other
    val phone_number: String,
    val password: String,
    val password_confirmation: String
)

