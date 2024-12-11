package com.dicoding.instahelp.API

data class InstitutionRegistrationRequest(
    val role: String,  // "resident"
    val name: String,  // Can be empty, but make sure to validate it properly
    val email: String,
    val address: String,
    val username: String,  // Added username field
    val longitude: Float,
    val description: String,
    val latitude: Float,
    val password: String,
    val password_confirmation: String
)