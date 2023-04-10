package com.example.remoteshop.fragments

import android.os.Bundle
import android.os.Looper
import android.text.Editable
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
import com.example.remoteshop.databinding.FragmentClientRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Handler
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

            username.text = Editable.Factory.getInstance().newEditable("Era")
            email.text = Editable.Factory.getInstance().newEditable("erdaulet03@gmail.com")
            city.text = Editable.Factory.getInstance().newEditable("Shymkent")
            password.text = Editable.Factory.getInstance().newEditable("123456")
            conf_pass.text = Editable.Factory.getInstance().newEditable("123456")

            if(username.text.toString().isEmpty()) username.error = "Empty username"
            else if(!email.text.toString().matches(emailPattern)) email.error = "Invalid email!"
            else if(city.text.toString().isEmpty()) city.error = "Empty city"
            else if(password.text.toString().length <5) password.error = "Password must be more than 6"
            else if(conf_pass.text.toString() != password.text.toString()) conf_pass.error = "Don't same passwords"
            else{

                val temp = Client(
                    null,
                    username.text.toString(),
                    email.text.toString(),
                    city.text.toString(),
                    password.text.toString()
                )

                val retrofit = api_instance.getApiInstance()
                val service = retrofit.create(api_services::class.java)

                CoroutineScope(Dispatchers.IO).launch {
                    val response = service.createClient(temp)

                    Thread(Runnable {
                        activity?.runOnUiThread(java.lang.Runnable {
                            Toast.makeText(activity, "${response.message()}", Toast.LENGTH_SHORT).show()
                        })
                    }).start()

                    Log.d("message", "${response.message()}")
                    Log.d("tostring", "${response.body().toString()} message")
                    Log.d("issuc", "${response.isSuccessful}")
                }
            }

        }

        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = ClientRegister()
    }
}