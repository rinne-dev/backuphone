package dev.rinne.backuphone.service

import androidx.preference.PreferenceManager
import dev.rinne.backuphone.App

class PreferenceService {
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getContext())

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}
