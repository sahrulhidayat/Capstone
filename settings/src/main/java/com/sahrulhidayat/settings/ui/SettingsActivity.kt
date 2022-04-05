package com.sahrulhidayat.settings.ui

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.sahrulhidayat.settings.databinding.ActivitySettingsBinding
import com.sahrulhidayat.settings.di.settingsModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SettingsActivity : AppCompatActivity() {

    private var _activitySettingsBinding: ActivitySettingsBinding? = null
    private val binding get() = _activitySettingsBinding

    private val viewModel by viewModel<SettingsViewModel>()

    private val loadFeatures by lazy { loadKoinModules(settingsModule) }
    private fun injectFeatures() = loadFeatures

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        injectFeatures()
        setThemeMode()

        binding?.switchTheme?.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            viewModel.setThemeSettings(isChecked)
        }
    }

    private fun setThemeMode() {
        viewModel.getThemeSettings().observe(this) { isDarkMode ->
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding?.switchTheme?.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding?.switchTheme?.isChecked = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activitySettingsBinding = null
    }
}