package com.dicoding.instahelp.resident.call

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.instahelp.R
import com.dicoding.instahelp.resident.call.amburecycler.Ambulance
import com.dicoding.instahelp.resident.call.amburecycler.AmbulanceAdapter

class DetailHospitalActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var ambulanceAdapter: AmbulanceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hospital)

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerViewAmbulance)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Data contoh untuk ambulans
        val ambulanceList = listOf(
            Ambulance("Ambulans Gawat Darurat", "John Doe", "BK 9999 GD", true),
            Ambulance("Ambulans Jenazah", "Jane Doe", "BK 3456 JZ", false),
            Ambulance("Ambulans Gawat Darurat", "Mark Smith", "BK 1234 ABC", true)
        )

        // Inisialisasi Adapter dan set ke RecyclerView
        ambulanceAdapter = AmbulanceAdapter(ambulanceList)
        recyclerView.adapter = ambulanceAdapter
    }
}
