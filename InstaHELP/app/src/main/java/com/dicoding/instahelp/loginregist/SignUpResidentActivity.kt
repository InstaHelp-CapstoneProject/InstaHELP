package com.dicoding.instahelp.loginregist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.instahelp.R

class SignUpResidentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_resident)

        val spinnerGender: Spinner = findViewById(R.id.sp_jenis_kelamin)

        // Array dari resources
        val genderOptions = resources.getStringArray(R.array.gender_options)

        // ArrayAdapter untuk Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter

        val toolbar = findViewById<View>(R.id.toolbar_signup)
        val rinciantext = toolbar.findViewById<TextView>(R.id.rincian)
        rinciantext.text = "Daftar Sekarang"
    }


}

