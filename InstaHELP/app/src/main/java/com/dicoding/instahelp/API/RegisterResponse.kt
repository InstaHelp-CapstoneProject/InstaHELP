package com.dicoding.instahelp.API

data class RegisterResponse(
    val success: Boolean,   // Menunjukkan status keberhasilan registrasi
    val message: String,    // Pesan status, misalnya "Registrasi berhasil" atau "Email sudah digunakan"
    val userId: String?     // ID pengguna yang dibuat setelah registrasi (jika diperlukan)
)
