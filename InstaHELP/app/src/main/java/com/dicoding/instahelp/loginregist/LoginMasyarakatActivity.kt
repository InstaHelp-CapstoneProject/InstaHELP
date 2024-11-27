package com.dicoding.instahelp.loginregist

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivityLoginMasyarakatBinding

class LoginMasyarakatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginMasyarakatBinding // Deklarasi binding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMasyarakatBinding.inflate(layoutInflater) // Inisialisasi binding
        setContentView(binding.root) // Set konten dengan root dari binding

        binding.eyeIcon.setOnClickListener { // Gunakan binding untuk mengakses view
            togglePasswordVisibility()
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.eyeIcon.setImageResource(R.drawable.eye_hid)
        } else {
            // Show password
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.eyeIcon.setImageResource(R.drawable.eye_reveal)
        }
        isPasswordVisible = !isPasswordVisible
        binding.etPassword.setSelection(binding.etPassword.text.length) // Move cursor to the end
    }
}