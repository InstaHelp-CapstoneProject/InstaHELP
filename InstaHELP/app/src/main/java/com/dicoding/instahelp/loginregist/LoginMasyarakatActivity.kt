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
import com.dicoding.instahelp.API.ResidentAuth
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivityLoginMasyarakatBinding
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

        binding.eyeIcon.setOnClickListener {
            togglePasswordVisibility()
        }

        val signupResident = findViewById<TextView>(R.id.btn_signup_resident)
        signupResident.setOnClickListener {
            // Navigasi ke SignUpResidentActivity
            val intent = Intent(this, SignUpResidentActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        val loginResident: TextView = findViewById(R.id.btn_next)
        loginResident.setOnClickListener {
            loginUser ()
        }

        val forgotPass: TextView = findViewById(R.id.tv_forgot_password_resident)
        forgotPass.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        loginResident.text = getString(R.string.masuk)
    }

    private fun loginUser () {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val residentAuth = ResidentAuth(email = email, password = password)

        // Panggil API untuk login
        ApiClient.apiService.loginUser(residentAuth).enqueue(object : Callback<ResidentAuth> {
            override fun onResponse(call: Call<ResidentAuth>, response: Response<ResidentAuth>) {
                if (response.isSuccessful) {
                    val loggedInUser  = response.body()
                    Log.d("LoginMasyarakatActivity", "User  logged in: $loggedInUser ")
                    Toast.makeText(this@LoginMasyarakatActivity, "Login berhasil!", Toast.LENGTH_SHORT).show()
                    // Navigasi ke activity berikutnya setelah login berhasil
                    // Misalnya: startActivity(Intent(this@LoginMasyarakatActivity, HomeActivity::class.java))
                } else {
                    Log.e("LoginMasyarakatActivity", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(this@LoginMasyarakatActivity, "Login gagal. Periksa kredensial Anda.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResidentAuth>, t: Throwable) {
                Log.e("LoginMasyarakatActivity", "Failure: ${t.message}")
                Toast.makeText(this@LoginMasyarakatActivity, "Terjadi kesalahan. Silakan coba lagi.", Toast.LENGTH_SHORT).show()
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
            // Hide password
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.eyeIcon.setImageResource(R.drawable.eye_hid)
        } else {
            // Show password
            binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            binding.eyeIcon.setImageResource(R.drawable.eye_reveal)
        }
        isPasswordVisible = !isPasswordVisible
        binding.etPassword.setSelection(binding.etPassword.text.length)
    }
}