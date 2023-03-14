package com.example.bookstore.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityAdminModeBinding
import com.example.bookstore.db.Books.bookDb
import com.example.bookstore.fragments.bookAdapter

class Admin_mode : AppCompatActivity() {

    lateinit var binding: ActivityAdminModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookDb = bookDb.getBookDb(this)
        bookDb.getBookDao().getAllBooks().asLiveData().observe(this) {
            var listBooks = it
            var adapter = bookAdminAdapter(listBooks)
            binding.recView.layoutManager = LinearLayoutManager(this)
            binding.recView.adapter = adapter
        }
    }
}