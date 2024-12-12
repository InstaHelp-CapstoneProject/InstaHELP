package com.dicoding.instahelp.loginregist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.API.ApiClient
import com.dicoding.instahelp.API.InstitutionRegistrationRequest
import com.dicoding.instahelp.API.RegisterResponse
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivitySignUpInstansiBinding
import com.dicoding.instahelp.resident.ResidentMainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpInstansiActivity : AppCompatActivity() {

    private lateinit var etPassword: EditText
    private lateinit var eyeIconPassword: ImageView
    private lateinit var etConfirmPass: EditText
    private lateinit var eyeIconConfirmPass: ImageView
    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_instansi)

        // Inisialisasi elemen UI
        etPassword = findViewById(R.id.et_password)
        eyeIconPassword = findViewById(R.id.eye_icon_password)
        etConfirmPass = findViewById(R.id.et_confirmpass)
        eyeIconConfirmPass = findViewById(R.id.eye_icon_confirmpass)

        // Toggle visibility untuk Kata Sandi
        eyeIconPassword.setOnClickListener {
            togglePasswordVisibility(
                etPassword,
                eyeIconPassword,
                isPasswordVisible
            ) { isPasswordVisible = !isPasswordVisible }
        }

        // Toggle visibility untuk Konfirmasi Kata Sandi
        eyeIconConfirmPass.setOnClickListener {
            togglePasswordVisibility(
                etConfirmPass,
                eyeIconConfirmPass,
                isConfirmPasswordVisible
            ) { isConfirmPasswordVisible = !isConfirmPasswordVisible }
        }

        val haveAcc: TextView = findViewById(R.id.tv_login_now)
        haveAcc.setOnClickListener {
            val intent = Intent(this, LoginInstansiActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_zoom_out, R.anim.activity_zoom_in)
        }

        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            val intent = Intent(this, LoginInstansiActivity::class.java)
            startActivity(intent)
        }

        val toolbar = findViewById<View>(R.id.toolbar_signup_instance)
        val detailText = toolbar.findViewById<TextView>(R.id.detail)
        detailText.text = getString(R.string.daftar_sekarang)

        val makeAcc: TextView = findViewById(R.id.btn_next)
        makeAcc.setOnClickListener {
            val name = findViewById<EditText>(R.id.et_name_instance).text.toString()
            val email = findViewById<EditText>(R.id.et_email).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            val phone = findViewById<EditText>(R.id.et_phone_instance).text.toString()
            val confirm_confirmation= findViewById<EditText>(R.id.et_confirmpass).text.toString()
            val address = findViewById<EditText>(R.id.et_address).text.toString()

            // Validasi input
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm_confirmation.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirm_confirmation) {
                Toast.makeText(this, "Kata sandi dan konfirmasi kata sandi tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val registerRequest = InstitutionRegistrationRequest(
                role = "institution",  // Role set to "institution"
                name = name,
                email = email,
                address = address,
                username = name,
                longitude = 107.69549F,  // Example value, replace with real input
                latitude = -6.925487F,  // Example value, replace with real input
                description = "RSU Bidadari didirikan pada 16 November 2006 dengan 40 tempat tidur, kini berkembang menjadi 200 tempat tidur dan lebih dari 350 tenaga medis serta non medis.",  // Example description
                password = password,
                password_confirmation = confirm_confirmation
            )

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Panggil API untuk registrasi
            ApiClient.apiService.registerInstitution(registerRequest).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        val token = registerResponse?.token
                        Log.d("SignUpInstansiActivity", "Registration successful: $registerResponse")

                        // Simpan token ke SharedPreferences
                        if (token != null) {
                            val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("bearer_token", token)
                            editor.apply()
                        } else {
                            Log.e("SignUpInstansiActivity", "Token is null")
                        }
                        Toast.makeText(this@SignUpInstansiActivity, "Registrasi berhasil!",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignUpInstansiActivity, LoginInstansiActivity::class.java))
                    } else {
                        val error = response.errorBody()?.string()
                        Log.e("SignUpInstansiActivity", "Error: $error")
                        Toast.makeText(this@SignUpInstansiActivity, "Registrasi gagal: $error}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e("SignUpInstansiActivity", "Failure: ${t.message}")
                    Toast.makeText(this@SignUpInstansiActivity, "Terjadi kesalahan. Silakan coba lagi.", Toast.LENGTH_SHORT).show()
                }
            })
        }

        makeAcc.text = getString(R.string.buat_akun)
    }

    private fun togglePasswordVisibility(
        editText: EditText,
        imageView: ImageView,
        isVisible: Boolean,
        toggleState: () -> Unit
    ) {
        if (isVisible) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.eye_hid)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.eye_reveal)
        }
        toggleState()
        editText.setSelection(editText.text.length)
    }
}
