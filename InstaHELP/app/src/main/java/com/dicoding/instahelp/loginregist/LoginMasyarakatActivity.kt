package com.dicoding.instahelp.loginregist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.API.ApiClient
import com.dicoding.instahelp.API.LoginResidentRequest
import com.dicoding.instahelp.API.LoginResponse
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivityLoginMasyarakatBinding
import com.dicoding.instahelp.resident.ResidentMainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginMasyarakatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginMasyarakatBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginMasyarakatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toggling visibility password
        binding.eyeIcon.setOnClickListener {
            togglePasswordVisibility()
        }

        // Navigasi ke halaman SignUp
        binding.btnSignupResident.setOnClickListener {
            val intent = Intent(this, SignUpResidentActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        // Aksi login ketika tombol login ditekan (menggunakan TextView)
        val loginResident: TextView = findViewById(R.id.btn_next)
        loginResident.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // Validasi input email dan password
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val loginRequest = LoginResidentRequest(email, password)
                performLogin(loginRequest) // Panggil fungsi login di sini
            } else {
                Toast.makeText(this, "Email atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigasi ke halaman forgot password
        binding.tvForgotPasswordResident.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        loginResident.text = getString(R.string.masuk)
    }

    // Fungsi login yang dipanggil saat tombol login ditekan
    private fun performLogin(loginRequest: LoginResidentRequest) {
        ApiClient.apiService.loginResident(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Toast.makeText(applicationContext, "Login Sukses: ${loginResponse?.message}", Toast.LENGTH_LONG).show()
                    // Pindah ke MainActivity setelah login sukses
                    val intent = Intent(this@LoginMasyarakatActivity, ResidentMainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out)
                } else {
                    Toast.makeText(applicationContext, "Login Gagal", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Terjadi Kesalahan: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, SelectRoleActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out)
        finish()
    }

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
}
