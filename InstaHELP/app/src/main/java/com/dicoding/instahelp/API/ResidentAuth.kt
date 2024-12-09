package com.dicoding.instahelp.API

data class ResidentAuth(
    val email: String,
    val password: String,
    val id: Int? = null,
    val name: String? = null,
    val token: String? = null
)
