package com.dicoding.instahelp.API

data class Institutions(
    val id: Int,
    val name: String,
    val email: String,
    val address: String,
    val longitude: String,
    val latitude: String,
    val description: String
)