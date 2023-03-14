package com.example.bookstore.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityAdminModeBinding
import com.example.bookstore.db.Books.bookDb
import com.example.bookstore.fragments.AdminMode
import com.example.bookstore.fragments.addBook
import com.example.bookstore.fragments.bookAdapter

class Admin_mode : AppCompatActivity() {

    lateinit var binding: ActivityAdminModeBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Admin Mode"
//        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        val bookDb = bookDb.getBookDb(this)
        bookDb.getBookDao().getAllBooks().asLiveData().observe(this) {
            var listBooks = it
            var adapter = bookAdminAdapter(listBooks, this)
            binding.recView.layoutManager = LinearLayoutManager(this)
            binding.recView.adapter = adapter
        }


        binding.addBookBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentAddBook, addBook.newInstance()).commit()
            supportActionBar?.title  = "Add Book"
            supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        }

        binding.exitAdmin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}