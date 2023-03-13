package com.example.bookstore.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.asLiveData
import com.example.bookstore.R
import com.example.bookstore.activities.Sing_In
import com.example.bookstore.databinding.FragmentProfileBinding
import com.example.bookstore.db.bookUsers.User
import com.example.bookstore.db.bookUsers.userDb

class Profile : Fragment() {

    lateinit private var binding: FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val contextOfActivity = requireActivity()
        val db = userDb.getUserDb(contextOfActivity)

        db.getDao().getAllUser().asLiveData().observe(viewLifecycleOwner){
            binding.nameProfile.text = it[it.size-1].name
            binding.surnameProfile.text =  it[it.size-1].surname
            binding.emailProfile.text =  it[it.size-1].email
            binding.passwordProfile.text =  it[it.size-1].password
        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = Profile()
    }
}