package com.dicoding.instahelp.API

data class InstitutionRegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
    val address: String?,
    val password_confirmation: String
)