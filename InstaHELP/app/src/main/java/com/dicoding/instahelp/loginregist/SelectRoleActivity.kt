<<<<<<< HEAD
package com.dicoding.instahelp.loginregist
=======
<<<<<<<< HEAD:InstaHELP/app/src/main/java/com/dicoding/instahelp/resident/call/CallActivity.kt
package com.dicoding.instahelp.resident.call
========
package com.dicoding.instahelp.loginregist
>>>>>>>> origin/main:InstaHELP/app/src/main/java/com/dicoding/instahelp/loginregist/SelectRoleActivity.kt
>>>>>>> origin/main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.instahelp.R
<<<<<<< HEAD
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
=======

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_call)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
>>>>>>> origin/main
        }
    }
}