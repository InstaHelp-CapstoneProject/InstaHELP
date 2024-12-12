package com.dicoding.instahelp.resident.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.enableSavedStateHandles
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.instahelp.API.Institutions
import com.dicoding.instahelp.API.InstitutionsService
import com.dicoding.instahelp.R
import com.dicoding.instahelp.resident.call.recycleradapter.InstitutionAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PanggilFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InstitutionAdapter
    private lateinit var editTextSearch: EditText
    private val apiService = InstitutionsService.create() // Buat instance service

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_panggil, container, false)
        enableSavedStateHandles()

        // Inisialisasi RecyclerView
        recyclerView = view.findViewById(R.id.recycler_hospitals)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Inisialisasi EditText untuk pencarian
        editTextSearch = view.findViewById(R.id.search_box)
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Tidak digunakan
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.isNotEmpty()) {
                    searchInstitutions(s.toString()) // Panggil pencarian hanya jika ada input
                } else {
                    getAllInstitutions() // Jika kosong, tampilkan semua institusi
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Tidak digunakan
            }
        })

        // Tampilkan data awal
        getAllInstitutions()

        return view
    }

    private fun getAllInstitutions() {
        val call = apiService.getInstitutions(null) // Panggil semua institusi tanpa filter
        call.enqueue(object : Callback<List<Institutions>> {
            override fun onResponse(call: Call<List<Institutions>>, response: Response<List<Institutions>>) {
                if (response.isSuccessful) {
                    val institutions = response.body()
                    institutions?.let {
                        adapter = InstitutionAdapter(it)
                        recyclerView.adapter = adapter
                    }
                } else {
                    // Tangani error response
                }
            }

            override fun onFailure(call: Call<List<Institutions>>, t: Throwable) {
                // Tangani kegagalan
            }
        })
    }

    private fun searchInstitutions(query: String) {
        val call = apiService.searchInstitutions(query) // Panggil API pencarian
        call.enqueue(object : Callback<List<Institutions>> {
            override fun onResponse(call: Call<List<Institutions>>, response: Response<List<Institutions>>) {
                if (response.isSuccessful) {
                    val institutions = response.body()
                    institutions?.let {
                        adapter = InstitutionAdapter(it)
                        recyclerView.adapter = adapter
                    }
                } else {
                    // Tangani error response
                }
            }

            override fun onFailure(call: Call<List<Institutions>>, t: Throwable) {
                // Tangani kegagalan
            }
        })
    }
}
