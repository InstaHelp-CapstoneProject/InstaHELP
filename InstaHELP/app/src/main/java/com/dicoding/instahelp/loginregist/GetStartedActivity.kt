package com.dicoding.instahelp.loginregist

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class GetStartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(com.dicoding.instahelp.R.layout.activity_get_started)

        // Mendapatkan referensi ke tombol
        val btnGetStarted = findViewById<TextView>(com.dicoding.instahelp.R.id.btn_get_started)

        // Menetapkan klik listener untuk tombol
        btnGetStarted.setOnClickListener {
            // Menavigasi ke SelectRoleActivity
            val intent = Intent(this, SelectRoleActivity::class.java)
            startActivity(intent)
        }
    }
}