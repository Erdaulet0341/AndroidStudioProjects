package com.example.newsus.savedItems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsus.ItemDetails
import com.example.newsus.MainActivity
import com.example.newsus.NewsAdapter
import com.example.newsus.R
import com.example.newsus.databinding.ActivitySavedNewsBinding

class SavedNews : AppCompatActivity() {
    lateinit var binding: ActivitySavedNewsBinding
    lateinit var recAdapter: sAdapter
    var backPressedTime: Long = 0
    lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedNewsBinding.inflate(layoutInflater)
        builder = AlertDialog.Builder(this)
        setContentView(binding.root)

        supportActionBar?.title = "Saved News"

        initRecyclerView()

    }

    private fun initRecyclerView() {
        var recyclerView  = binding.recyclerSave

        recyclerView.apply {
            val db = sDb.getNewsDb(this@SavedNews)
            db.getNewsDao().getAllSavedNews().asLiveData().observe(this@SavedNews){
                if(it.size == 0){
                    Toast.makeText(this@SavedNews, "No saved news!!!", Toast.LENGTH_SHORT).show()
                }
                layoutManager = LinearLayoutManager(this@SavedNews)
                recAdapter = sAdapter(it, this@SavedNews)
                recAdapter.setOnItemClickListener(object : sAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        intent = Intent(this@SavedNews, ItemDetails::class.java)
                        intent.putExtra("title", it[position].title)
                        intent.putExtra("description", it[position].description)
                        intent.putExtra("author", it[position].author)
                        intent.putExtra("content", it[position].content)
                        intent.putExtra("imgUrl", it[position].urlToImage)
                        startActivity(intent)
                    }
                })
                adapter = recAdapter
                recAdapter.notifyDataSetChanged()
            }
        }
    }
    override fun onBackPressed() {
        if (backPressedTime + 10 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            builder.setTitle("Exit Saves News")
                .setMessage("Do you want to log out from saved news ?")
                .setPositiveButton("Yes"){id, it ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("No"){id, it ->
                    id.cancel()
                }
                .show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}