package com.dicoding.instahelp.loginregist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.instahelp.R
import android.view.View
import android.widget.LinearLayout

class SelectRoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_role)

        val cardMasyarakat = findViewById<LinearLayout>(R.id.card_masyarakat)
        val cardInstansi = findViewById<LinearLayout>(R.id.card_instansi)
        val toggleMasyarakat = findViewById<View>(R.id.toggle_masyarakat)
        val toggleInstansi = findViewById<View>(R.id.toggle_instansi)

        cardMasyarakat.setOnClickListener {
            toggleMasyarakat.isSelected = true
            toggleInstansi.isSelected = false
        }

        cardInstansi.setOnClickListener {
            toggleInstansi.isSelected = true
            toggleMasyarakat.isSelected = false
        }
    }
}