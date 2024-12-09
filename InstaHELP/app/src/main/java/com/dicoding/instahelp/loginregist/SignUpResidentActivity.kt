package com.dicoding.instahelp.loginregist

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.API.ApiClient
import com.dicoding.instahelp.API.RegisterResponse
import com.dicoding.instahelp.API.ResidentRegistrationRequest
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivitySignUpResidentBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpResidentActivity : AppCompatActivity() {

    private lateinit var etPassword: EditText
    private lateinit var eyeIconPassword: ImageView
    private lateinit var etConfirmPass: EditText
    private lateinit var eyeIconConfirmPass: ImageView
    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_resident)

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

        // Inisialisasi Spinner Jenis Kelamin
        val spinnerGender: Spinner = findViewById(R.id.sp_gender)
        val genderOptions = resources.getStringArray(R.array.gender_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter

        val etDateBirth: EditText = findViewById(R.id.et_date_birth)
        val calenderIcon: ImageView = findViewById(R.id.calender)

        val calendar = Calendar.getInstance()

        // DatePicker Dialog
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                etDateBirth.setText(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Show DatePickerDialog on clicking EditText or ImageView
        etDateBirth.setOnClickListener { datePicker.show() }
        calenderIcon.setOnClickListener { datePicker.show() }

        val haveAcc: TextView = findViewById(R.id.tv_login_now)
        haveAcc.setOnClickListener {
            val intent = Intent(this, LoginMasyarakatActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_zoom_out, R.anim.activity_zoom_in)
        }

        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener{
            val intent = Intent(this, LoginMasyarakatActivity::class.java)
            startActivity(intent)
        }

        val toolbar = findViewById<View>(R.id.toolbar_signup_resident)
        val detailText = toolbar.findViewById<TextView>(R.id.detail)
        detailText.text = getString(R.string.daftar_sekarang)

        val makeAcc: TextView = findViewById(R.id.btn_next)
        makeAcc.setOnClickListener {
            val name = findViewById<EditText>(R.id.et_name).text.toString()
            val email = findViewById<EditText>(R.id.et_email).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.et_confirmpass).text.toString()
            val gender = spinnerGender.selectedItem.toString()
            val dateOfBirth = findViewById<EditText>(R.id.et_date_birth).text.toString()
            val nik = findViewById<EditText>(R.id.et_nik).text.toString()
            val address = findViewById<EditText>(R.id.et_address).text.toString()
            val phoneNumber = findViewById<EditText>(R.id.et_phone).text.toString()

            // Validasi input
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender.isEmpty() || dateOfBirth.isEmpty() || nik.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Kata sandi dan konfirmasi kata sandi tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val registerRequest = ResidentRegistrationRequest(
                name = name,
                email = email,
                password = password,
                password_confirmation = confirmPassword,
                gender = gender,
                date_of_birth = dateOfBirth,
                place_of_birth = "",  // Bisa kosong atau tambahkan input untuk tempat lahir jika diperlukan
                nik = nik,
                phone_number = phoneNumber,
                address = address
            )

            // Panggil API untuk registrasi
            ApiClient.apiService.registerResident(registerRequest).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        Log.d("SignUpResidentActivity", "Registration successful: $registerResponse")
                        Toast.makeText(this@SignUpResidentActivity, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUpResidentActivity, LoginMasyarakatActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.e("SignUpResidentActivity", "Error: ${response.errorBody()?.string()}")
                        Toast.makeText(this@SignUpResidentActivity, "Registrasi gagal. Coba lagi.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e("SignUpResidentActivity", "Failure: ${t.message}")
                    Toast.makeText(this@SignUpResidentActivity, "Terjadi kesalahan. Silakan coba lagi.", Toast.LENGTH_SHORT).show()
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
            // Hide password
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.eye_hid)

        } else {
            // Show password
            editText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imageView.setImageResource(R.drawable.eye_reveal)
        }
        toggleState()
        editText.setSelection(editText.text.length)
    }
}
