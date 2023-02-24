package com.cookingapp

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.preference.PreferenceFragmentCompat
import com.cookingapp.ui.theme.*


class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.title = "Settings"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




    }

    class SettingsFragment : PreferenceFragmentCompat() {
        private val key1 = "mode"
        private var preferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener? =
            null

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            preferenceChangeListener =
                OnSharedPreferenceChangeListener { sharedPreferences, key ->
                    if (key == key1) {

                    }
                }
        }

    }
}