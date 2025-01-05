package com.resep.mymenu_resep_kue

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvkue: RecyclerView
    private val list = ArrayList<Kue>()

    companion object{
        val INTENT_PARCEABLE = "OBJECT_INTENT"
    }

    // RecyclerView rv_kucings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvkue = findViewById(R.id.rv_kue)
        rvkue.setHasFixedSize(true)

        list.addAll(getListkue())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvkue.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvkue.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, Aboutme::class.java)
                startActivity(moveIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //Mengambil data pada string
    private fun getListkue(): ArrayList<Kue> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDetail = resources.getStringArray(R.array.data_detail)
        val dataKatagori = resources.getStringArray(R.array.data_kategori)
        val listkue = ArrayList<Kue>()
        for (i in dataName.indices) {
            val Kue = Kue(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataKatagori[i], dataDetail[i])
            listkue.add(Kue)
        }
        return listkue
    }

    private fun showRecyclerList() {
        rvkue.layoutManager = LinearLayoutManager(this)
        val listResepkueAdapter = ListResepkueAdapter(list)
        rvkue.adapter = listResepkueAdapter

        listResepkueAdapter.setOnItemClickCallback(object : ListResepkueAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Kue) {
                showSelectedKue(data)
            }
        })

    }

    //Muncul pesan "Kamu memilih kue...
    private fun showSelectedKue(Kue: Kue) {
        Toast.makeText(this, "Kamu memilih " + Kue.name, Toast.LENGTH_SHORT).show()

        //Pindah ke halaman detail Kue
        val intent = Intent(this, Detailkue::class.java)
        intent.putExtra(INTENT_PARCEABLE, Kue)
        startActivity(intent)
    }

}