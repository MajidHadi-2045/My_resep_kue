package com.resep.mymenu_resep_kue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Aboutme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_about)

        supportActionBar?.apply {
            title = getString(R.string.about)
        }
    }
}