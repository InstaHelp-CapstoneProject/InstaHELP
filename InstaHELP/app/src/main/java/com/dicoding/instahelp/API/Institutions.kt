package com.dicoding.instahelp.API

data class Institutions(
    val id: Int,
    val name: String,
    val email: String,
    val address: String,
    val availability: String,
    val vehicleCount: Int,
    val longitude: String,
    val latitude: String,
    val description: String
) {

}