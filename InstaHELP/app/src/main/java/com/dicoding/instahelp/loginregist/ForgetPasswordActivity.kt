package com.dicoding.instahelp.loginregist

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.instahelp.R

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forget_password)

        val toolbar = findViewById<View>(R.id.toolbar_forgetPass)
        val rinciantext = toolbar.findViewById<TextView>(R.id.rincian)
        rinciantext.text = getString(R.string.lupa_kata_sandi)

        val forgetAcct: TextView = findViewById(R.id.btn_next)
        forgetAcct.text = getString(R.string.masuk)
    }
}