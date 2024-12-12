package com.dicoding.instahelp.loginregist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.API.ApiClient
import com.dicoding.instahelp.API.LoginInstitutionRequest
import com.dicoding.instahelp.API.LoginResponse
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivityLoginInstansiBinding
import com.dicoding.instahelp.resident.ResidentMainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInstansiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginInstansiBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginInstansiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eyeIcon.setOnClickListener {
            togglePasswordVisibility()
        }

        val loginInstance: TextView= findViewById(R.id.btn_next)
        loginInstance.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginInstitutionRequest(
                email = email,
                password = password
            )

            // Mengirim permintaan login
            ApiClient.apiService.loginInstitution(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        Log.d("LoginInstansiActivity", "Login successful: ${loginResponse?.message}")
                        // Simpan token ke SharedPreferences
                        val token = loginResponse?.message
                        Log.d("LoginInstansiActivity", "Token received: $token")

                        token?.let {
                            val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("bearer_token", it)
                            editor.apply()
                        }
                        Toast.makeText(this@LoginInstansiActivity, "Login berhasil!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginInstansiActivity, ResidentMainActivity::class.java))
                        finish()
                    } else {
                        val error = response.errorBody()?.string()
                        Log.e("LoginInstansiActivity", "Error: $error")
                        Toast.makeText(this@LoginInstansiActivity, "Login gagal: $error", Toast.LENGTH_SHORT).show()
                    }
                }


                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("LoginInstansiActivity", "Failure: ${t.message}")
                    Toast.makeText(this@LoginInstansiActivity, "Terjadi kesalahan. Silakan coba lagi.", Toast.LENGTH_SHORT).show()
                }
            })
        }

        // Tombol untuk pindah ke halaman SignUp
        val signupInstance = binding.btnSignupInstance
        signupInstance.setOnClickListener {
            val intent = Intent(this, SignUpInstansiActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // Tombol untuk forgot password
        val forgotPass: TextView = binding.tvForgotPasswordInstance
        forgotPass.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    // Fungsi untuk menampilkan atau menyembunyikan password
    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.eyeIcon.setImageResource(R.drawable.eye_hid)
        } else {
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.eyeIcon.setImageResource(R.drawable.eye_reveal)
        }
        isPasswordVisible = !isPasswordVisible
        binding.etPassword.setSelection(binding.etPassword.text.length)
    }

    // Menangani tombol back untuk kembali ke aktivitas sebelumnya
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, SelectRoleActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out)
        finish()
    }
}