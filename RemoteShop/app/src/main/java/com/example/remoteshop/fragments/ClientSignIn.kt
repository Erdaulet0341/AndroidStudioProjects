package com.example.remoteshop.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.remoteshop.R
import com.example.remoteshop.backend.api_instance
import com.example.remoteshop.backend.api_services
import com.example.remoteshop.backend.users.Client
import com.example.remoteshop.databinding.FragmentClientSignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class ClientSignIn : Fragment() {
    lateinit var binding: FragmentClientSignInBinding
    val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientSignInBinding.inflate(inflater)
        binding.progressBarClientSingin.visibility = View.INVISIBLE

        binding.registerClient.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.client_frag, ClientRegister.newInstance())?.commit()
            (activity as AppCompatActivity).supportActionBar?.title = "Client Registration"
        }

        binding.clientEnter.setOnClickListener {
            var email = binding.clientloginIn
            var password = binding.clientPasswordIn

            if(!email.text.toString().matches(emailPattern)) email.error = "Invalid email!"
            else if(password.text.toString().isEmpty()) password.error = "Emoty password!"
            else{
                checkLogin()
            }
        }

        return binding.root
    }


    private fun checkLogin() {
        val api = api_instance.getApiInstance().create(api_services::class.java)
        val call = api.getAllClients()
        binding.progressBarClientSingin.visibility = View.VISIBLE
        call.enqueue(object : Callback<List<Client>> {
            override fun onResponse(call: Call<List<Client>>, response: Response<List<Client>>) {
                var clients = response.body()

                Log.d("clients", "${clients?.size}")

                Toast.makeText(activity, "responce work", Toast.LENGTH_SHORT).show()
                binding.progressBarClientSingin.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<List<Client>>, t: Throwable) {
                Toast.makeText(activity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                Log.d("clients", "${t.message}")
                binding.progressBarClientSingin.visibility = View.INVISIBLE

            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClientSignIn()
    }
}