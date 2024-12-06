package com.dicoding.instahelp.loginregist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivityLoginMasyarakatBinding

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
            val intent = Intent(this, SignUpResidentActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out)
        }
        val forgotPass: TextView = findViewById(R.id.tv_forgot_password_resident)
        forgotPass.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


        loginResident.text = getString(R.string.masuk)

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
