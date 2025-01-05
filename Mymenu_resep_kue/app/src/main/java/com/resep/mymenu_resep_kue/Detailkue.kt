package com.resep.mymenu_resep_kue

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Detailkue : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_myresepkue)

        // Menetapkan title pada action bar
        supportActionBar?.apply {
            title = getString(R.string.detail_kue)
        }

        // Ambil data parcelable dari Intent dengan kompatibilitas SDK 33+
        val kueDetail: Kue? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(MainActivity.INTENT_PARCEABLE, Kue::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(MainActivity.INTENT_PARCEABLE)
        }

        // Inisialisasi view
        val photo = findViewById<ImageView>(R.id.photo_img)
        val name = findViewById<TextView>(R.id.jenis_kue)
        val kategori = findViewById<TextView>(R.id.Kategori)
        val detail = findViewById<TextView>(R.id.description)

        // Update UI dengan data dari parcelable
        kueDetail?.let {
            photo.setImageResource(it.photo)
            name.text = it.name
            kategori.text = it.kategori
            detail.text = it.detail
        }
    }

    // Inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Handle menu item click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_share -> {
                // Ambil data untuk dibagikan
                val kueDetail: Kue? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(MainActivity.INTENT_PARCEABLE, Kue::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra(MainActivity.INTENT_PARCEABLE)
                }

                // Buat teks untuk dibagikan
                kueDetail?.let {
                    val shareText = """
                        Jenis Kue: ${it.name}
                        Kategori: ${it.kategori}
                        Detail: ${it.detail}
                    """.trimIndent()

                    // Intent untuk membagikan data ke WhatsApp, Instagram, Telegram, Gmail
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }

                    // Cek jika ingin membagikan ke aplikasi tertentu
                    when (item.itemId) {
                        R.id.btn_whatsapp -> {
                            shareIntent.setPackage("com.whatsapp")
                        }
                        R.id.btn_instagram -> {
                            shareIntent.setPackage("com.instagram.android")
                        }
                        R.id.btn_telegram -> {
                            shareIntent.setPackage("org.telegram.messenger")
                        }
                        R.id.btn_gmail -> {
                            val mailto = "mailto:"
                            shareIntent.apply {
                                action = Intent.ACTION_SENDTO
                                data = Uri.parse(mailto)
                                putExtra(Intent.EXTRA_SUBJECT, "Kue yang saya temukan")
                                putExtra(Intent.EXTRA_TEXT, shareText)
                            }
                        }
                    }

                    // Jika tidak ada aplikasi yang dipilih, tampilkan chooser
                    if (shareIntent.resolveActivity(packageManager) != null) {
                        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)))
                    } else {
                        // Menampilkan pesan jika aplikasi tidak terpasang
                        Toast.makeText(this, "Aplikasi tidak tersedia", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
