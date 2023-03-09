package com.example.myapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainMenu : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val nav_menu = findViewById<NavigationView>(R.id.navigation_menu);
        supportFragmentManager.beginTransaction().replace(R.id.mapFrame, home_buttons()).commit()


        nav_menu.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile ->{
                    val intent = Intent(this, Profile_settings::class.java)
                    startActivity(intent)
                }
                R.id.likeCars ->{
                    Toast.makeText(this@MainMenu, "You don't liked any cars!", Toast.LENGTH_SHORT).show()
                }
                R.id.setting->{
                    Toast.makeText(this@MainMenu, "Мен ностройканы алы жасап улгермедым))) ", Toast.LENGTH_SHORT).show()
                }
                R.id.logout ->{
                    finish()
                }
            }
            true
        }

        val bottom_menu = findViewById<BottomNavigationView>(R.id.bottomMenu)

        bottom_menu.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.menuLeft ->{
                    val drw = findViewById<DrawerLayout>(R.id.drawer)
                    drw.openDrawer(GravityCompat.START)
                }
                R.id.homeBottom ->{
                    Toast.makeText(this@MainMenu, "Home", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.mapFrame, home_buttons()).commit()
                }
                R.id.navigationBottom ->{
                    Toast.makeText(this@MainMenu, "Map", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.mapFrame, MapFragment()).commit()
                }
            }
            true
        }

    }
}