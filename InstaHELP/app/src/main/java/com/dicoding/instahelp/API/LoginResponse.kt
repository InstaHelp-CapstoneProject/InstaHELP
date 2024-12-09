package com.dicoding.instahelp.API

data class LoginResponse(
    val success: Boolean,   // Menunjukkan status keberhasilan login
    val message: String,    // Pesan status, misalnya "Login berhasil" atau "Invalid credentials"
    val token: String?      // Token autentikasi (seperti JWT) yang digunakan untuk akses API lebih lanjut
)
