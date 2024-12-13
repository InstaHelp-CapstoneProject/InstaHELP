package com.dicoding.instahelp.resident.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.instahelp.API.Institutions
import com.dicoding.instahelp.API.InstitutionsService
import com.dicoding.instahelp.R
import com.dicoding.instahelp.resident.call.recycleradapter.InstitutionAdapter
import com.dicoding.instahelp.resident.call.DetailHospitalActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PanggilFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InstitutionAdapter
    private lateinit var editTextSearch: EditText
    private val apiService = InstitutionsService.create() // Buat instance service

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_panggil, container, false)

        recyclerView = view.findViewById(R.id.recycler_hospitals)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = InstitutionAdapter(getDummyInstitutions(), { institution ->
            // Klik pada tombol Call
            val bottomSheet = CallBottomSheetFragment()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }, { institution ->
            // Klik pada item RecyclerView
            val intent = Intent(requireContext(), DetailHospitalActivity::class.java).apply {
                putExtra("EXTRA_INSTITUTION", institution)
            }
            startActivity(intent)
        })
        recyclerView.adapter = adapter

        return view
    }

    private fun getDummyInstitutions(): List<Institutions> {
        return listOf(
            Institutions(
                id = 1,
                name = "RS Harapan Sehat",
                email = "rs1@example.com",
                address = "Jl. Sehat No. 1",
                availability = "Tersedia",
                vehicleCount = 5,
                longitude = "2.5",
                latitude = "-6.9",
                description = "Rumah sakit dengan fasilitas lengkap."
            ),
            Institutions(
                id = 2,
                name = "RS Medika Prima",
                email = "rs2@example.com",
                address = "Jl. Utama No. 2",
                availability = "Tidak tersedia",
                vehicleCount = 2,
                longitude = "1.2",
                latitude = "-6.5",
                description = "Pelayanan terbaik di bidang kesehatan."
            )
        )
    }
}
/*
    private fun getAllInstitutions() {
        val call = apiService.getInstitutions(null) // Panggil semua institusi tanpa filter
        call.enqueue(object : Callback<List<Institutions>> {
            override fun onResponse(call: Call<List<Institutions>>, response: Response<List<Institutions>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("API Response", it.toString())
                        adapter = InstitutionAdapter(it) { institution ->
                            val intent = Intent(requireContext(), DetailHospitalActivity::class.java).apply {
                                putExtra("EXTRA_INSTITUTION", institution)
                            }
                            startActivity(intent)
                        }
                        recyclerView.adapter = adapter
                    }
                } else {
                    Log.e("API Error", response.message())
                }
            }

            override fun onFailure(call: Call<List<Institutions>>, t: Throwable) {
                Log.e("API Failure", t.message ?: "Error tidak diketahui")
            }
        })
    }

    private fun searchInstitutions(query: String) {
        val call = apiService.searchInstitutions(query) // Panggil API pencarian
        call.enqueue(object : Callback<List<Institutions>> {
            override fun onResponse(call: Call<List<Institutions>>, response: Response<List<Institutions>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("API Response", it.toString())
                        adapter = InstitutionAdapter(it) { institution ->
                            val intent = Intent(requireContext(), DetailHospitalActivity::class.java).apply {
                                putExtra("EXTRA_INSTITUTION", institution)
                            }
                            startActivity(intent)
                        }
                        recyclerView.adapter = adapter
                    }
                } else {
                    Log.e("API Error", response.message())
                }
            }

            override fun onFailure(call: Call<List<Institutions>>, t: Throwable) {
                Log.e("API Failure", t.message ?: "Error tidak diketahui")
            }
        })
    }
}
*/