package com.resep.mymenu_resep_kue

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Menggunakan coroutine untuk delay
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000) // 3 detik
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}

