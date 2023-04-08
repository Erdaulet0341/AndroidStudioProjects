package com.example.remoteshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.remoteshop.R
import com.example.remoteshop.databinding.FragmentSellerSignInBinding
import java.util.regex.Pattern

class SellerSignIn : Fragment() {
    lateinit var binding: FragmentSellerSignInBinding
    val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSellerSignInBinding.inflate(inflater)

        binding.registerSeller.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.seller_frag, SellerRegister.newInstance())?.commit()
            (activity as AppCompatActivity).supportActionBar?.title = "Seller Registration"
        }

        binding.sellerEnter.setOnClickListener {
            var email = binding.sellerloginIn
            var password = binding.sellerPasswordIn

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
        fun newInstance() = SellerSignIn()
    }
}