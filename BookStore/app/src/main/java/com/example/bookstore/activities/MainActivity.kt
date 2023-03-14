package com.example.bookstore.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityMainBinding
import com.example.bookstore.fragments.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var builder: AlertDialog.Builder
    var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        builder = AlertDialog.Builder(this)
        setContentView(binding.root)
        supportActionBar?.title = "Book Store"

        supportFragmentManager.beginTransaction().replace(R.id.fragment, showBooks.newInstance()).commit()

        binding.bottomBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu ->{
                    binding.drawer.openDrawer(GravityCompat.START)
                    supportActionBar?.title = "Menu"
                    Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
                }
                R.id.topBooks ->{
                    supportActionBar?.title = "Book Store"
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, showBooks.newInstance()).commit()
                    Toast.makeText(this, "Books", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        binding.leftNav.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile ->{
                    supportActionBar?.title = "Profile"
                    Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, Profile.newInstance()).commit()
                }
                R.id.admin ->{
                    supportActionBar?.title = "Admin mode"
                    Toast.makeText(this, "admin", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, AdminMode.newInstance()).commit()
                }
                R.id.setting ->{
                    supportActionBar?.title = "Settings"
                    Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, Settings.newInstance()).commit()
                }
                R.id.about ->{
                    supportActionBar?.title = "About App"
                    Toast.makeText(this, "about", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, About.newInstance()).commit()
                }
                R.id.logout ->{
                    builder.setTitle("Exit accaunt")
                        .setMessage("Do you want to log out,then you'll have to log in again!")
                        .setPositiveButton("Yes"){dialogInterface, it ->
                            val intent = Intent(this, First_Welcome::class.java)
                            startActivity(intent)
                        }
                        .setNegativeButton("No"){id, it ->
                            id.cancel()
                        }.show()
                }
            }
            true
        }

    }


    override fun onBackPressed() {
        if (backPressedTime + 10 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            builder.setTitle("Exit your account")
                .setMessage("Do you want to log out your account,then you'll have to log in again!")
                .setPositiveButton("Yes"){id, it ->
                    val intent = Intent(this, First_Welcome::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("No"){id, it ->
                    id.cancel()
                }
                .show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}