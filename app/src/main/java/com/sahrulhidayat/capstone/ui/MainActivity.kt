package com.sahrulhidayat.capstone.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sahrulhidayat.capstone.R
import com.sahrulhidayat.capstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appbarMain.toolbar)

        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as DynamicNavHostFragment).navController
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}