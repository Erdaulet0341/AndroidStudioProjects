package com.example.remoteshop.fragments.ClientFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.remoteshop.R
import com.example.remoteshop.activities.FirstWelcome
import com.example.remoteshop.databinding.FragmentHomePageBinding

class HomePage : Fragment() {

    lateinit var binding: FragmentHomePageBinding
    lateinit var builder: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater)
        builder = AlertDialog.Builder(requireContext())

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    builderText()
                }
            }
        )
        return binding.root
    }


    private fun builderText() {
        builder.setTitle("Exit Client Account")
            .setMessage("Do you want to log out from client account,then you'll have to log in again!")
            .setPositiveButton("Yes"){id, it ->
                val intent = Intent(activity, FirstWelcome::class.java)
                startActivity(intent)
            }
            .setNegativeButton("No"){id, it ->
                id.cancel()
            }
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance()=HomePage()

    }
}