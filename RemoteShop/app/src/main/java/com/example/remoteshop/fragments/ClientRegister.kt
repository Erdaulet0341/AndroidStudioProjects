package com.example.remoteshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.remoteshop.R
import com.example.remoteshop.databinding.FragmentClientRegisterBinding
import com.example.remoteshop.databinding.FragmentClientSignInBinding
import com.example.remoteshop.databinding.FragmentSellerRegisterBinding
import java.util.regex.Pattern

class ClientRegister : Fragment() {
    lateinit var  binding: FragmentClientRegisterBinding
    val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientRegisterBinding.inflate(inflater)

        binding.SingInRegClient.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.client_frag, ClientSignIn.newInstance())?.commit()
            (activity as AppCompatActivity).supportActionBar?.title = "Client Sign In"
        }

        binding.clientRegBtn.setOnClickListener {
            var username = binding.clientusernameIn
            var email = binding.clientemailIn
            var city = binding.clientcitynameIn
            var password = binding.clientpassRegIn
            var conf_pass = binding.clientpassRegConfIn

            if(username.text.toString().isEmpty()) username.error = "Empty username"
            else if(!email.text.toString().matches(emailPattern)) email.error = "Invalid email!"
            else if(city.text.toString().isEmpty()) city.error = "Empty city"
            else if(password.text.toString().length <5) password.error = "Password must be more than 6"
            else if(conf_pass.text.toString() != password.text.toString()) conf_pass.error = "Don't same passwords"
//            else{
//
//            }

        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ClientRegister()
    }
}