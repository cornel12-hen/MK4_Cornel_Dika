package com.cornel.a10_31_latihan4_recyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cornel.a10_31_latihan4_recyclerview.adapter.CardViewMyDataAdapter
import com.cornel.a10_31_latihan4_recyclerview.adapter.GridMyDataAdapter
import com.cornel.a10_31_latihan4_recyclerview.adapter.ListMyDataAdapter
import com.cornel.a10_31_latihan4_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            rvMydata.setHasFixedSize(true)
            list.addAll(getListMyData())
            showRecyclerList()
        }
    }
    private fun getListMyData(): ArrayList<MyData> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listMyData = ArrayList<MyData>()
        for (position in dataName.indices) {
            val myData = MyData(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listMyData.add(myData)
        }
        return listMyData
    }
    private fun showRecyclerList() {
        binding.apply {
            rvMydata.layoutManager = LinearLayoutManager(this@MainActivity)
            val listMyDataAdapter = ListMyDataAdapter(list)
            rvMydata.adapter = listMyDataAdapter
        }
    }
    private val list = ArrayList<MyData>()
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                showRecyclerList()
            }
            R.id.action_grid -> {
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                showRecyclerCardView()
            }
        }
    }
    private fun showRecyclerGrid() {
        binding.apply {
            rvMydata.layoutManager = GridLayoutManager(this@MainActivity, 2)
            val gridMyDataAdapter = GridMyDataAdapter(list)
            rvMydata.adapter = gridMyDataAdapter
        }
    }
    private fun showRecyclerCardView() {
        binding.apply {
            rvMydata.layoutManager = LinearLayoutManager(this@MainActivity)
            val cardViewMyDataAdapter = CardViewMyDataAdapter(list)
            rvMydata.adapter = cardViewMyDataAdapter
        }
    }
}