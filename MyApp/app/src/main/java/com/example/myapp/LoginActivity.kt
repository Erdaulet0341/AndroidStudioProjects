package com.example.myapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin);
        val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()
        val progressDialog = ProgressDialog(this@LoginActivity)
        val Auth = FirebaseAuth.getInstance()

        var btn = findViewById<TextView>(R.id.textViewSingUp)
        btn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if(!email.matches(emailPattern)){
                inputEmail.setError("Enter correct email!")

            }
            else if(password.isEmpty() || password.length<6){
                inputPassword.setError("Enter available password(more than 6 character)")
            }
            else {
                progressDialog.setTitle("Login...")
                progressDialog.setMessage("Login in progress, please wait...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        progressDialog.dismiss()
                        sendNextActivity()
                        Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext, "Login or password incorrect!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun sendNextActivity(){
        val intent = Intent(this, MainMenu::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK/Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}