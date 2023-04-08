package com.example.remoteshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.remoteshop.R
import com.example.remoteshop.databinding.FragmentClientSignInBinding
import java.util.regex.Pattern

class ClientSignIn : Fragment() {
    lateinit var binding: FragmentClientSignInBinding
    val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientSignInBinding.inflate(inflater)

        binding.registerClient.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.client_frag, ClientRegister.newInstance())?.commit()
            (activity as AppCompatActivity).supportActionBar?.title = "Client Registration"
        }

        binding.clientEnter.setOnClickListener {
            var email = binding.clientloginIn
            var password = binding.clientPasswordIn

            if(!email.text.toString().matches(emailPattern)) email.error = "Invalid email!"
            else if(password.text.toString().isEmpty()) password.error = "Emoty password!"
//            else{
//
//            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClientSignIn()
    }
}