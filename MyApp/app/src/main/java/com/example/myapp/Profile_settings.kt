package com.example.myapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Profile_settings : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_settings)

        val btn = findViewById<TextView>(R.id.back_setting)
        btn.setOnClickListener{
            finish()
        }

//        val inputUsername = findViewById<EditText>(R.id.inputUsernameReg)
//        val inputEmail = findViewById<EditText>(R.id.inputEmail)
//        val inputPassword = findViewById<EditText>(R.id.inputPassword)
//        val inputCanformPassword = findViewById<EditText>(R.id.inputConformPassword)
//
//        val username = inputUsername.text.toString()
//        val email = inputEmail.text.toString()
//        val password = inputPassword.text.toString()
//        val coPassword = inputCanformPassword.text.toString()
//        val username_set = findViewById<EditText>(R.id.username_set)
//        username_set.text = Editable.Factory.getInstance().newEditable("era")
//        username_set.text = inputUsername.text
    }
}
