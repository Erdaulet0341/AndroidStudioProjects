package com.example.remoteshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.remoteshop.R
import com.example.remoteshop.databinding.FragmentClientRegisterBinding
import com.example.remoteshop.databinding.FragmentSellerRegisterBinding
import java.util.regex.Pattern

class SellerRegister : Fragment() {

    lateinit var binding: FragmentSellerRegisterBinding
    val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSellerRegisterBinding.inflate(inflater)


        binding.SingInReg.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.seller_frag, SellerSignIn.newInstance())?.commit()
            (activity as AppCompatActivity).supportActionBar?.title = "Seller Sign In"
        }

        binding.sellerRegBtn.setOnClickListener {
            var username = binding.sellerusernameIn
            var email = binding.selleremailIn
            var company_name = binding.sellercompanynameIn
            var password = binding.sellerpassRegIn
            var conf_pass = binding.sellerpassRegConfIn

            if(username.text.toString().isEmpty()) username.error = "Empty username"
            else if(!email.text.toString().matches(emailPattern)) email.error = "Invalid email!"
            else if(company_name.text.toString().isEmpty()) company_name.error = "Empty Company name"
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
        fun newInstance() = SellerRegister()
    }
}