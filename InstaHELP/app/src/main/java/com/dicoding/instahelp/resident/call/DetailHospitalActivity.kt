package com.dicoding.instahelp.resident.call

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.instahelp.R
import com.dicoding.instahelp.resident.call.recycleradapter.Ambulance
import com.dicoding.instahelp.resident.call.recycleradapter.AmbulanceAdapter
import com.dicoding.instahelp.resident.fragment.CallBottomSheetFragment

class DetailHospitalActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var ambulanceAdapter: AmbulanceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hospital)

        // Data contoh untuk ambulans
        val ambulanceList = listOf(
            Ambulance("Ambulans Gawat Darurat", "John Doe", "BK 9999 GD", true),
            Ambulance("Ambulans Jenazah", "Jane Doe", "BK 3456 JZ", false),
            Ambulance("Ambulans Gawat Darurat", "Mark Smith", "BK 1234 ABC", true)
        )

        // Inisialisasi RecyclerView dan Adapter
        recyclerView = findViewById(R.id.recyclerViewAmbulance)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set Adapter dengan Listener
        ambulanceAdapter = AmbulanceAdapter(ambulanceList) { ambulance ->
            // Tampilkan BottomSheet saat item diklik
        }
        recyclerView.adapter = ambulanceAdapter

        val emergencyButton: Button = findViewById(R.id.button_call_emergency)
        emergencyButton.setOnClickListener {
            val bottomSheet = CallBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

    }
}
