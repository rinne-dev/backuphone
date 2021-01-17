package dev.rinne.backuphone

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceFragmentCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }

        val requiredPermissions = ArrayList<String>()
        if (checkPermission(Manifest.permission.INTERNET)) {
            requiredPermissions.add(Manifest.permission.INTERNET)
        }
        if (checkPermission(Manifest.permission.READ_SMS)) {
            requiredPermissions.add(Manifest.permission.READ_SMS)
        }
        if (checkPermission(Manifest.permission.RECEIVE_SMS)) {
            requiredPermissions.add(Manifest.permission.RECEIVE_SMS)
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(permissions: Array<String>) {
        ActivityCompat.requestPermissions(this, permissions, 1)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)
        }
    }
}
