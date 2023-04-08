package com.example.remoteshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.remoteshop.R
import com.example.remoteshop.databinding.ActivityLoginAdminBinding

class LoginAdmin : AppCompatActivity() {
    lateinit var binding: ActivityLoginAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Admin login"


        binding.adminEnter.setOnClickListener {

            val login = binding.adminloginIn
            val password = binding.adminPasswordIn

            if (login.text.toString().isEmpty()) login.error = "Empty login"
            else if (password.text.toString().isEmpty()) password.error = "Empty password"
//            else{
//
//            }
        }

    }
}