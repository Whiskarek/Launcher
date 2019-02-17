package by.whiskarek.yandexlauncher

import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

abstract class BaseActivity: AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(::onSharedPreferenceChanged)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(::onSharedPreferenceChanged)
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

    protected open fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "pref_theme" -> recreate()
        }
    }
}