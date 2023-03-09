package com.example.myapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val inputCanformPassword = findViewById<EditText>(R.id.inputConformPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()
        val progressDialog = ProgressDialog(this@RegisterActivity)
        val Auth = FirebaseAuth.getInstance()

        val btn = findViewById<TextView>(R.id.alreadyHaveAccount)
        btn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        
        btnRegister.setOnClickListener{
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            val coPassword = inputCanformPassword.text.toString()
            if(!email.matches(emailPattern)){
                inputEmail.setError("Enter correct email!")

            }
            else if(password.isEmpty()){
                inputPassword.setError("Password is empty!")
            }
            else if(password.length<6){
                inputPassword.setError("Enter password more than 6 symbol!")
            }
            else if(!password.equals(coPassword)){
                inputCanformPassword.setError("Passwords don't same!")

            }
            else{
                progressDialog.setTitle("Registration...")
                progressDialog.setMessage("Registration in progress, please wait...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        progressDialog.dismiss()
                        sendNextActivity()
                        Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_SHORT).show()
                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext, ""+task.exception, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
   private fun sendNextActivity(){
       val intent = Intent(this, FirstWelcome::class.java)
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK/Intent.FLAG_ACTIVITY_NEW_TASK)
       startActivity(intent)
   }
}