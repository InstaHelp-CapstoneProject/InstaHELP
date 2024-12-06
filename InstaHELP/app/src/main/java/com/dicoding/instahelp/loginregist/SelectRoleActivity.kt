package com.dicoding.instahelp.loginregist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.R

class SelectRoleActivity : AppCompatActivity() {
    private var isResidentSelected = false
    private var isInstanceSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_role)

        val cardResident = findViewById<LinearLayout>(R.id.card_resident)
        val cardInstance = findViewById<LinearLayout>(R.id.card_instance)
        val toggleResident = findViewById<View>(R.id.toggle_resident)
        val toggleInstance = findViewById<View>(R.id.toggle_instance)

        // Pengecekan role yang dipilih
        cardResident.setOnClickListener {
            toggleResident.isSelected = true
            toggleInstance.isSelected = false
            isResidentSelected = true
            isInstanceSelected = false
        }

        cardInstance.setOnClickListener {
            toggleInstance.isSelected = true
            toggleResident.isSelected = false
            isResidentSelected = false
            isInstanceSelected = true
        }

        // Mendapatkan referensi ke tombol "Selanjutnya"
        val btnNext = findViewById<TextView>(R.id.btn_next)

        // Menetapkan klik listener untuk tombol "Selanjutnya"
        btnNext.setOnClickListener {
            if (isResidentSelected || isInstanceSelected) {
                val intent = if (isResidentSelected) {
                    Intent(this, LoginMasyarakatActivity::class.java)
                } else {
                    Intent( this, LoginInstansiActivity::class.java)
                }
                startActivity(intent)
                overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out)
                finish() // Menutup SelectRoleActivity setelah navigasi
            } else {
                // Menampilkan pesan jika role belum dipilih
                Toast.makeText(this, "Pilih salah satu peran terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

    }


}
