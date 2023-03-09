package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FirstWelcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_welcome)
        val btn = findViewById<Button>(R.id.singUpbtn)
        btn.setOnClickListener{
            val intn = Intent(this, RegisterActivity::class.java);
            startActivity(intn)
        }
        val btn2 = findViewById<Button>(R.id.singInnbt)
        btn2.setOnClickListener{
            val intn2 = Intent(this, LoginActivity::class.java);
            startActivity(intn2)
        }
    }
}