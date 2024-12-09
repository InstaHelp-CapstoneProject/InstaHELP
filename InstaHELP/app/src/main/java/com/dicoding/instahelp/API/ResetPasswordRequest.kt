package com.dicoding.instahelp.API

data class ResetPasswordRequest(
    val password: String,
    val password_confirmation: String
)
