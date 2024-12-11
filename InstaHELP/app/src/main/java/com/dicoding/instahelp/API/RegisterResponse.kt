package com.dicoding.instahelp.API

data class RegisterResponse(
    val status: Boolean,
    val message: String,
    val token: String?  // The bearer token
)

