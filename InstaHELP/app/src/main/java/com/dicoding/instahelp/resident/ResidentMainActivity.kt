package com.dicoding.instahelp.resident

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.instahelp.R
import androidx.fragment.app.Fragment
import com.dicoding.instahelp.resident.fragment.PanggilFragment

import com.dicoding.instahelp.resident.fragment.PengaturanFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ResidentMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resident_main)

        // Default fragment
        loadFragment(PanggilFragment())

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.panggilFragment -> {
                    loadFragment(PanggilFragment())
                    true
                }

                R.id.pengaturanFragment -> {
                    loadFragment(PengaturanFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}