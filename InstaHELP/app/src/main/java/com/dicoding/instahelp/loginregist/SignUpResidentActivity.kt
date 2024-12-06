package com.dicoding.instahelp.loginregist

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivityLoginMasyarakatBinding
import com.dicoding.instahelp.databinding.ActivitySignUpResidentBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
                val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
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
            val intent = Intent(this, LoginMasyarakatActivity::class.java)
            startActivity(intent) // Memulai aktivitas SignUpResidentActivity
            overridePendingTransition(R.anim.activity_zoom_out, R.anim.activity_zoom_in)
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

