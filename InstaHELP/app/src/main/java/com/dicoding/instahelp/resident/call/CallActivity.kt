<<<<<<<< HEAD:InstaHELP/app/src/main/java/com/dicoding/instahelp/resident/call/CallActivity.kt
package com.dicoding.instahelp.resident.call
========
package com.dicoding.instahelp.loginregist
>>>>>>>> origin/main:InstaHELP/app/src/main/java/com/dicoding/instahelp/loginregist/SelectRoleActivity.kt

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.instahelp.R

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_call)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}