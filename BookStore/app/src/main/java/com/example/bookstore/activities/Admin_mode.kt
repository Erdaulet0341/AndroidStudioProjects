package com.example.bookstore.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityAdminModeBinding
import com.example.bookstore.db.Books.bookDb
import com.example.bookstore.fragments.addBook
import com.example.bookstore.fragments.bookAdapter

class Admin_mode : AppCompatActivity() {

    lateinit var binding: ActivityAdminModeBinding

    @SuppressLint("RestrictedApi")
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


        binding.addBookBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentAddBook, addBook.newInstance()).commit()
            supportActionBar?.title  = "Add Book"
            supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        }

    }
}