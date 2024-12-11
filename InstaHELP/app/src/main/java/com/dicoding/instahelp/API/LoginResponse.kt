package com.dicoding.instahelp.API

data class LoginResponse(
    val success: Boolean,   // Menunjukkan status keberhasilan login
    val message: String   // Token autentikasi (seperti JWT) yang digunakan untuk akses API lebih lanjut
)
