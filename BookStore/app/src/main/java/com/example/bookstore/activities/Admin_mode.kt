package com.example.bookstore.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
    lateinit var builder: AlertDialog.Builder
    var backPressedTime: Long = 0

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminModeBinding.inflate(layoutInflater)
        builder = AlertDialog.Builder(this)
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
            builder.setTitle("Exit Admin mode")
                .setMessage("Do you want to log out from admin mode,then you'll have to log in again!")
                .setPositiveButton("Yes"){id, it ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("No"){id, it ->
                    id.cancel()
                }
                .show()
        }
    }
    override fun onBackPressed() {
        if (backPressedTime + 10 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            builder.setTitle("Exit Admin mode")
                .setMessage("Do you want to log out from admin mode,then you'll have to log in again!")
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