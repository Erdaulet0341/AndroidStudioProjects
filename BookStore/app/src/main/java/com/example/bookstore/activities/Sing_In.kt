package com.example.bookstore.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.example.bookstore.R
import com.example.bookstore.databinding.SingInBinding
import com.example.bookstore.db.bookUsers.userDb

class Sing_In : AppCompatActivity() {
    lateinit var binding: SingInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SingInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Login"

        val db = userDb.getUserDb(this)

        binding.singInBtn.setOnClickListener {
            var email = binding.singInEmail.text.toString()
            var pass = binding.singInPassword.text.toString()

            if(!email.isEmpty() && !pass.isEmpty()){
                db.getDao().getAllUser().asLiveData().observe(this){list ->
                    var check:Boolean = true
                    list.forEach{
                        if(email.equals(it.email) && pass.equals(pass)){
                            Log.i(TAG, "${it.password} and ${pass}")

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            check = true
                            return@forEach
                        }
                        else{
                            check = false
                        }
                    }
                    if(!check){
                        Toast.makeText(this, "Incorrect data!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
            }

        }

    }
}