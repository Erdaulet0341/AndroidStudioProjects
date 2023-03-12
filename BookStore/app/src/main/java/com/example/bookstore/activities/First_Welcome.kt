package com.example.bookstore.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityFirstWelcomeBinding

class First_Welcome : AppCompatActivity() {
    lateinit var binding: ActivityFirstWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Welcome Book Store"

        binding.singin.setOnClickListener {
            val intent = Intent(this, Sing_In::class.java)
            startActivity(intent)
        }

        binding.singup.setOnClickListener {
            val intent = Intent(this, Sing_Up::class.java)
            startActivity(intent)
        }
    }
}