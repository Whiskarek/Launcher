package by.whiskarek.yandexlauncher.activity

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import by.whiskarek.yandexlauncher.R

abstract class BaseActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onResume() {
        super.onResume()
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun getTheme(): Resources.Theme {
        val theme = super.getTheme()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themeType = sharedPreferences.getString("pref_theme", "light")?.toString()
        when (themeType) {
            "light" -> theme.applyStyle(R.style.AppThemeLight, true)
            "dark" -> theme.applyStyle(R.style.AppThemeDark, true)
        }
        return theme
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "pref_theme" -> recreate()
        }
    }
}
