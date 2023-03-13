package com.example.bookstore.fragments

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentSettingsBinding
import com.example.bookstore.db.bookUsers.User
import com.example.bookstore.db.bookUsers.userDb
import java.util.regex.Pattern

class Settings : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater)

        val currentActivity = requireActivity()
        val db = userDb.getUserDb(currentActivity)
        var currentUser: User
        val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+").toRegex()


        db.getDao().getAllUser().asLiveData().observe(viewLifecycleOwner){
            binding.nameSetting.text =  Editable.Factory.getInstance().newEditable(it[it.size-1].name)
            binding.surnameSetting.text =   Editable.Factory.getInstance().newEditable(it[it.size-1].surname)
            binding.emailSetting.text =  Editable.Factory.getInstance().newEditable(it[it.size-1].email)
            binding.passwordSetting.text =   Editable.Factory.getInstance().newEditable(it[it.size-1].password)
        }

        binding.saveSettingBtn.setOnClickListener {
            if(binding.nameSetting.text.isEmpty()) binding.nameSetting.error = "Empty name"
            else if(binding.surnameSetting.text.isEmpty()) binding.surnameSetting.error = ("Empty surname")
            else if(!binding.emailSetting.text.matches(emailPattern)) binding.emailSetting.error = "Enter correct email!"
            else if(binding.passwordSetting.text.length < 6) binding.passwordSetting.error = "Enter password more than 6 symbol!"
            else if( !binding.nameSetting.text.isEmpty()
                && !binding.surnameSetting.text.isEmpty()
                && !binding.emailSetting.text.isEmpty()
                && !binding.passwordSetting.text.isEmpty())
            {
                db.getDao().getAllUser().asLiveData().observe(viewLifecycleOwner){
                    currentUser = it[it.size-1]
                    val int = currentUser.id as Int
                    Thread{
                        db.getDao().updateUser(int,
                            binding.nameSetting.text.toString(),
                            binding.surnameSetting.text.toString(),
                            binding.emailSetting.text.toString(),
                            binding.passwordSetting.text.toString(),
                            binding.passwordSetting.text.toString())
                    }.start()
                }
                Toast.makeText(currentActivity, "Changes saved", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(currentActivity, "Empty data!", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = Settings()
    }
}