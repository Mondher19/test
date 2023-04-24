package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityAdmin : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main_admin)



        // Find the BottomNavigationView by its ID
        bottomNavigationView = findViewById(R.id.bottomNavigationViewadmin)

        // Set the listener for the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Home -> {
                    changeFragment(HomeAdmin(), "HomeFragment")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.Stations -> {
                    changeFragment(ChooseTransportAdmin(), "stationsadmin")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.Transports -> {
                    changeFragment(transportadmin(), "transportadmin")
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.Reclamtions -> {
                    changeFragment(ReclamationAdmin(), "Reclamtionsadmin")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.Users -> {
                    changeFragment(usersadmin(), "usersadmin")
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    private fun changeFragment(fragment: Fragment, name: String) {
        if (name.isEmpty())
            supportFragmentManager.beginTransaction().replace(R.id.switchfragment, fragment).commit()
        else
            supportFragmentManager.beginTransaction().replace(R.id.switchfragment, fragment).addToBackStack(name).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_admin_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Handle other menu items here
            else -> return super.onOptionsItemSelected(item)
        }
    }




    }
