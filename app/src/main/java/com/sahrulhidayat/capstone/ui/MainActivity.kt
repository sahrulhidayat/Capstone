package com.sahrulhidayat.capstone.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sahrulhidayat.capstone.R
import com.sahrulhidayat.capstone.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val settingsViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        setTheme(R.style.Theme_Capstone_NoActionBar)
        super.onCreate(savedInstanceState)

        setThemeMode()

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
            R.id.settings -> {
                val intent = Intent()
                intent.setClassName(
                    this,
                    "com.sahrulhidayat.settings.ui.SettingsActivity"
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setThemeMode() {
        settingsViewModel.getThemeSettings().observe(this) { isDarkMode ->
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}