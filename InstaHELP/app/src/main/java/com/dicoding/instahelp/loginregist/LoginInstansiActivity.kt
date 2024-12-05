package com.dicoding.instahelp.loginregist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.R
import com.dicoding.instahelp.databinding.ActivityLoginInstansiBinding
import com.dicoding.instahelp.databinding.ActivityLoginMasyarakatBinding

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
        val signupInstansi = findViewById<TextView>(R.id.btn_signup_instansi)
        signupInstansi.setOnClickListener {
            // Navigasi ke SignUpResidentActivity
            val intent = Intent(this, SignUpResidentActivity::class.java)
            startActivity(intent)
        }
        val logininstansi: TextView = findViewById(R.id.btn_next)
        logininstansi.setOnClickListener {
            val intent = Intent(this, SignUpResidentActivity::class.java)
            startActivity(intent) // Memulai aktivitas SignUpResidentActivity
        }
        val forgot_password: TextView = findViewById(R.id.tv_forgot_password_instansi)
        forgot_password.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        logininstansi.text = getString(R.string.masuk)
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
