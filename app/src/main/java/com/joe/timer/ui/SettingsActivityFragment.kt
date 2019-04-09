package com.joe.timer.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.joe.timer.R

class SettingsActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}
