package com.dicoding.instahelp.API

data class PasswordResponse(
    val success: Boolean,   // Menunjukkan status keberhasilan pengaturan ulang password
    val message: String     // Pesan status, misalnya "Password berhasil direset" atau "Email tidak ditemukan"
)
