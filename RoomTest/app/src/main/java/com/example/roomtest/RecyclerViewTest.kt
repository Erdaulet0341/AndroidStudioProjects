package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomtest.databinding.ActivityRecyclerViewTestBinding

class RecyclerViewTest : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewTestBinding
    private val adapter = planAdapter()
    private val listImg = listOf(R.drawable.plant1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
    }

    private fun initRecycler(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@RecyclerViewTest)
//            rcView.layoutManager = GridLayoutManager(this@RecyclerViewTest, 3)
            rcView.adapter = adapter
            val plant = Plant(listImg[0], "Plant ${0}")
            buttonAdd.setOnClickListener {

                adapter.addPlant(plant)
            }
        }
    }
}