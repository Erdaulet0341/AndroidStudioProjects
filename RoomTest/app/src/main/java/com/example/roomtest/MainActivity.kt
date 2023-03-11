package com.example.roomtest

import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.roomtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDb.getDb(this)
        db.getDao().getAllEvenItems().asLiveData().observe(this){list ->
            binding.tvList.text = ""
            list.forEach{
                val itemText = "Id=${it.id}, name=${it.name}, price=${it.price}\n"
                binding.tvList.append(itemText)
            }
        }
        binding.SaveDatas.setOnClickListener {
            val item = Item(
                null,
                binding.name.text.toString(),
                binding.Price.text.toString()
            )
            Thread{
                db.getDao().insertItem(item)
                binding.name.text.clear()
                binding.Price.text.clear()
            }.start()
        }
    }
}