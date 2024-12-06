package com.dicoding.instahelp.resident.call.recycleradapter

data class Ambulance(
    val name: String,
    val driverName: String,
    val plateNumber: String,
    val isAvailable: Boolean
)

data class HospitalItem(
    val name: String,
    val location: String,
    val availability: String,
    val vehicleCount: Int,
    val vehicleIcon: Int, // Icon kendaraan
    val distance: String,
    val isVerified: Boolean // Untuk badge verifikasi
)

data class Report(
    val imageRes: Int,
    val title: String,
    val description: String,
    val time: String
)