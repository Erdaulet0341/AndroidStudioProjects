package com.example.remoteshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.remoteshop.R
import com.example.remoteshop.databinding.ActivityLoginClientBinding
import com.example.remoteshop.fragments.ClientSignIn
class LoginClient : AppCompatActivity() {
    lateinit var binding: ActivityLoginClientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.client_frag, ClientSignIn.newInstance()).commit()
        supportActionBar?.title = "Client Sign In"

    }
}