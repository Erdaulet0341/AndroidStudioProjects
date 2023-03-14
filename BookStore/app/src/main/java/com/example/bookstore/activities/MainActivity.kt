package com.example.bookstore.activities

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.asLiveData
import com.example.bookstore.R
import com.example.bookstore.databinding.ActivityMainBinding
import com.example.bookstore.db.bookUsers.userDb
import com.example.bookstore.fragments.About
import com.example.bookstore.fragments.Profile
import com.example.bookstore.fragments.Settings
import com.example.bookstore.fragments.showBooks

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Book Store"

        supportFragmentManager.beginTransaction().replace(R.id.fragment, showBooks.newInstance()).commit()

//        val db =userDb.getUserDb(this)

        binding.bottomBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu ->{
                    binding.drawer.openDrawer(GravityCompat.START)
                    supportActionBar?.title = "Menu"
                    Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()
                }
                R.id.topBooks ->{
//                    db.getDao().getAllUser().asLiveData().observe(this){
//                        Log.i(ContentValues.TAG, "${it[0].email} and ${it[0].password}")
//                    }
                    supportActionBar?.title = "Book Store"
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, showBooks.newInstance()).commit()
                    Toast.makeText(this, "Books", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }
        binding.leftNav.setNavigationItemSelectedListener {
//            val drawerLayout = DrawerLayout(this@MainActivity)
            when(it.itemId){
                R.id.profile ->{
                    supportActionBar?.title = "Profile"
                    Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment, Profile.newInstance()).commit()
                }
                R.id.admin ->{
                    supportActionBar?.title = "Admin mode"
                    Toast.makeText(this, "admin", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
//            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_bar ->{
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

}