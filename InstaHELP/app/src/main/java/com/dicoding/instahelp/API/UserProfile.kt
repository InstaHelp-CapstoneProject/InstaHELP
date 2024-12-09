package com.dicoding.instahelp.API

data class UserProfile(
    val id: Int,
    val name: String,
    val email: String,
    val username: String,
    val email_verified_at: String?,  // Waktu verifikasi email
    val created_at: String,  // Waktu pembuatan akun
    val updated_at: String   // Waktu terakhir update
)