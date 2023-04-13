package com.example.remoteshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.remoteshop.R
import com.example.remoteshop.backend.api_instance
import com.example.remoteshop.backend.api_services
import com.example.remoteshop.backend.users.Client
import com.example.remoteshop.databinding.ActivityLoginAdminBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            else{
                val api = api_instance.getApiInstance().create(api_services::class.java)
                val call = api.getAllAdmins()
                binding.progressBarClientSingin.visibility = View.VISIBLE
                call.enqueue(object : Callback<List<Client>> {
                    override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
                        var clients = response.body()

                        Log.d("clients", "${clients?.size}")
                        Log.d("is_succesfull", "${response?.isSuccessful}")

                        Toast.makeText(activity, "responce work", Toast.LENGTH_SHORT).show()
                        binding.progressBarClientSingin.visibility = View.INVISIBLE
                    }

                    override fun onFailure(call: Call<List<Client>>, t: Throwable) {
                        Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
                        Log.d("clients", "${t.message}")
                        binding.progressBarClientSingin.visibility = View.INVISIBLE

                    }
                })
            }
        }

    }
}