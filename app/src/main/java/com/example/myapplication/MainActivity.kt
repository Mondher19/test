package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.myapplication.Adapters.stationAdapter
import com.example.myapplication.Adapters.transportAdapter
import com.example.myapplication.Model.station
import com.example.myapplication.Model.transport
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        // Find the BottomNavigationView by its ID
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Set the listener for the bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.report -> {
                    changeFragment(ReportFragment(), "ReportFragment")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.home -> {
                    changeFragment(ChooseTransportFragment(), "ChooseTransportFragment")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.settings -> {
                    changeFragment(SettingsFragment(), "SettingsFragment")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.map -> {
                    changeFragment(MapsFragment(), "MapsFragment")
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
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Handle other menu items here
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
