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
    private var isMasyarakatSelected = false
    private var isInstansiSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_select_role)

        val cardMasyarakat = findViewById<LinearLayout>(R.id.card_masyarakat)
        val cardInstansi = findViewById<LinearLayout>(R.id.card_instansi)
        val toggleMasyarakat = findViewById<View>(R.id.toggle_masyarakat)
        val toggleInstansi = findViewById<View>(R.id.toggle_instansi)

        // Pengecekan role yang dipilih
        cardMasyarakat.setOnClickListener {
            toggleMasyarakat.isSelected = true
            toggleInstansi.isSelected = false
            isMasyarakatSelected = true
            isInstansiSelected = false
        }

        cardInstansi.setOnClickListener {
            toggleInstansi.isSelected = true
            toggleMasyarakat.isSelected = false
            isInstansiSelected = true
            isMasyarakatSelected = false
        }

        // Mendapatkan referensi ke tombol "Selanjutnya"
        val btnNext = findViewById<TextView>(R.id.btn_next)

        // Menetapkan klik listener untuk tombol "Selanjutnya"
        btnNext.setOnClickListener {
            if (isMasyarakatSelected || isInstansiSelected) {
                val intent = if (isMasyarakatSelected) {
                    Intent(this, LoginMasyarakatActivity::class.java)
                } else {
                    Intent(this, LoginInstansiActivity::class.java) // Ganti dengan LoginInstansiActivity jika ada
                }
                startActivity(intent)
                finish() // Menutup SelectRoleActivity setelah navigasi
            } else {
                // Menampilkan pesan jika role belum dipilih
                Toast.makeText(this, "Pilih salah satu peran terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
