package com.dicoding.instahelp.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.instahelp.R
import com.dicoding.instahelp.loginregist.GetStartedActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            goToGetStartActivity()
        }, 3000L)
    }

    private fun goToGetStartActivity() {
        Intent(this, GetStartedActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}