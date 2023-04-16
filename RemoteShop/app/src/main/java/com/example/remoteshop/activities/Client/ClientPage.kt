package com.example.remoteshop.activities.Client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.remoteshop.R
import com.example.remoteshop.databinding.ActivityClientPageBinding
import com.example.remoteshop.databinding.ActivitySellerPageBinding
import com.example.remoteshop.fragments.SellerFragments.AddProductSeller
import com.example.remoteshop.fragments.SellerFragments.AllProductsSeller
import com.example.remoteshop.fragments.SellerFragments.SellerProfileFragment

class ClientPage : AppCompatActivity() {

    lateinit var binding: ActivityClientPageBinding
//    lateinit var bundle: Bundle
    lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        builder = AlertDialog.Builder(this)
//        bundle = intent.extras as Bundle
        supportActionBar?.title = "Home page"
//        val id = bundle!!.getInt("id")
        val client_id = 13
        Log.d("id", "$client_id")


        bottomBar()
    }

    private fun bottomBar() {
        binding.bottomBarClient.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homeClinet ->{
                    supportActionBar?.title = "Home page"
                    Toast.makeText(this@ClientPage, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.likes_client ->{
                    supportActionBar?.title = "Liked products"
                    Toast.makeText(this@ClientPage, "Liked products", Toast.LENGTH_SHORT).show()
                }
                R.id.cart_client ->{
                    supportActionBar?.title = "Cart"
                    Toast.makeText(this@ClientPage, "Cart", Toast.LENGTH_SHORT).show()
                }
                R.id.profile_client ->{
                    supportActionBar?.title = "Profile"
                    Toast.makeText(this@ClientPage, "Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.settings_clietn ->{
                    supportActionBar?.title = "Settings"
                    Toast.makeText(this@ClientPage, "Settings", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}