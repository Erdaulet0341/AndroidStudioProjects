package com.example.bookstore.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookstore.R
import com.example.bookstore.databinding.SingUpBinding
import com.example.bookstore.db.bookUsers.User
import com.example.bookstore.db.bookUsers.userDb
import java.util.regex.Pattern

class Sing_Up : AppCompatActivity() {
    lateinit var binding: SingUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Registration"

        val db = userDb.getUserDb(this)
        val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()
        binding.singUpBnt.setOnClickListener {
            var name = binding.nameUp.text.toString()
            var surname = binding.surnameUp.text.toString()
            var email = binding.emialUp.text.toString()
            var password = binding.passwUp.text.toString()
            var confpass = binding.confPassUp.text.toString()

            if(name.isEmpty()) binding.nameUp.setError("Empty name")
            else if(surname.isEmpty()) binding.surnameUp.setError("Empty surname")
            else if(!email.matches(emailPattern)) binding.emialUp.setError("Enter correct email!")
            else if(password.length < 6) binding.passwUp.setError("Enter password more than 6 symbol!")
            else if (!password.equals(confpass)) binding.confPassUp.setError("Dont same passwords!")
            else if(!name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confpass.isEmpty()){
                val tempUser = User(
                    null,
                    name,
                    surname,
                    email,
                    password,
                    confpass
                )
                Thread{
                    db.getDao().insertUser(tempUser)
                }.start()
                Toast.makeText(applicationContext, "Log In Successful", Toast.LENGTH_SHORT).show()
                val int = Intent(this, Sing_In::class.java)
                startActivity(int)
            }
            else{
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}