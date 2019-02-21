package by.whiskarek.yandexlauncher.activity

import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import by.whiskarek.yandexlauncher.Constants
import by.whiskarek.yandexlauncher.R
import java.lang.IllegalArgumentException

abstract class BaseActivity : AppCompatActivity() {

    protected var showWelcomePage
        get() = BaseActivity.showWelcomePage
        private set(value) {
            BaseActivity.showWelcomePage = value
        }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume(). Before registering OnSharedPreferenceChangeListener")
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this::onSharedPreferenceChanged)
        Log.d(TAG, "onResume(). After registering OnSharedPreferenceChangeListener")
        this.javaClass.asSubclass(this.javaClass)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause(). Before unregistering OnSharedPreferenceChangeListener")
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this::onSharedPreferenceChanged)
        Log.d(TAG, "onPause(). After unregistering OnSharedPreferenceChangeListener")
    }

    override fun setTheme(resid: Int) {
        Log.d(TAG, "setTheme()")
        if (currentTheme == -1) {
            Log.d(TAG, "setTheme(). Loading settings from sharedPreferences")
            loadSettingsFromSharedPreferences()
            Log.d(TAG, "setTheme(). Settings successfully loaded")
        }
        super.setTheme(currentTheme)
    }

    private fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Log.d(TAG, "onSharedPreferenceChanged()")
        if (sharedPreferences == null)
            return
        when (key) {
            Constants.PREFERENCE_KEY_THEME -> changeTheme(sharedPreferences, key)
            Constants.PREFERENCE_KEY_MODEL -> changeModel(sharedPreferences, key)
            Constants.PREFERENCE_KEY_SORT -> changeSort(sharedPreferences, key)
            Constants.PREFERENCE_KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD -> changeShowWelcomePage(sharedPreferences, key)
        }
    }

    private fun changeTheme(sharedPreferences: SharedPreferences, key: String) {
        currentTheme = loadThemeFromSharedPreferences(sharedPreferences, key)
        recreate()
        onThemeChanged(currentTheme)
    }

    private fun changeModel(sharedPreferences: SharedPreferences, key: String) {
        currentModel = loadModelFromSharedPreferences(sharedPreferences, key)
        onModelChanged(currentModel)
    }

    private fun changeSort(sharedPreferences: SharedPreferences, key: String) {
        currentSort = loadSortFromSharedPreferences(sharedPreferences, key)
        onSortChanged(currentSort)
    }

    private fun changeShowWelcomePage(sharedPreferences: SharedPreferences, key: String) {
        showWelcomePage = loadShowWelcomePageFromSharedPreferences(sharedPreferences, key)
        onShowWelcomePageChanged(showWelcomePage)
    }

    protected open fun onThemeChanged(newTheme: Int) {
        Log.d(TAG, "onThemeChanged(). Theme changed to $newTheme")
    }

    protected open fun onModelChanged(newModel: String) {
        Log.d(TAG, "onThemeChanged(). Model changed to $newModel")
    }

    protected open fun onSortChanged(newSort: String) {
        Log.d(TAG, "onThemeChanged(). Sort changed to $newSort")
    }

    protected open fun onShowWelcomePageChanged(showWelcomePage: Boolean) {
        Log.d(TAG, "onShowWelcomePageChanged(). ShowWelcomePage changed to $showWelcomePage")
    }

    private fun getTheme(themeType: String): Int = when (themeType) {
        "light" -> R.style.AppThemeLight
        "dark" -> R.style.AppThemeDark
        else -> throw IllegalArgumentException("Unknown theme type")
    }

    private fun loadThemeFromSharedPreferences(sharedPreferences: SharedPreferences, key: String): Int {
        val newThemeType = sharedPreferences.getString(key, "light")!!
        return getTheme(newThemeType)
    }

    private fun loadModelFromSharedPreferences(sharedPreferences: SharedPreferences, key: String): String =
        sharedPreferences.getString(key, "default")!!

    private fun loadSortFromSharedPreferences(sharedPreferences: SharedPreferences, key: String): String =
        sharedPreferences.getString(key, "alphabet_high")!!

    private fun loadShowWelcomePageFromSharedPreferences(sharedPreferences: SharedPreferences, key: String): Boolean =
        sharedPreferences.getBoolean(key, true)

    private fun loadSettingsFromSharedPreferences() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        currentTheme = loadThemeFromSharedPreferences(sharedPreferences, Constants.PREFERENCE_KEY_THEME)
        currentModel = loadModelFromSharedPreferences(sharedPreferences, Constants.PREFERENCE_KEY_MODEL)
        currentSort = loadSortFromSharedPreferences(sharedPreferences, Constants.PREFERENCE_KEY_SORT)
        showWelcomePage = loadShowWelcomePageFromSharedPreferences(
            sharedPreferences,
            Constants.PREFERENCE_KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD
        )
    }

    companion object {
        private const val TAG = "BaseActivity"

        private var currentTheme: Int = -1
        private var currentModel: String = "default"
        private var currentSort: String = "alphabet_high"
        private var showWelcomePage: Boolean = true
    }
}
