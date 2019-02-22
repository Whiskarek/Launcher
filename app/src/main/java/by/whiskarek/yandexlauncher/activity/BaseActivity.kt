package by.whiskarek.yandexlauncher.activity

import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import by.whiskarek.yandexlauncher.Constants
import java.lang.IllegalArgumentException

abstract class BaseActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val childClassName = this.javaClass.asSubclass(this.javaClass).simpleName
    protected var showWelcomePage
        get() = BaseActivity.showWelcomePage
        private set(value) {
            BaseActivity.showWelcomePage = value
        }
    var currentTheme
        get() = BaseActivity.currentTheme
        private set(value) {
            BaseActivity.currentTheme = value
        }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$childClassName.onResume(). Before registering OnSharedPreferenceChangeListener")
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
        Log.d(TAG, "$childClassName.onResume(). After registering OnSharedPreferenceChangeListener")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$childClassName.onPause(). Before unregistering OnSharedPreferenceChangeListener")
        PreferenceManager
            .getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
        Log.d(TAG, "$childClassName.onPause(). After unregistering OnSharedPreferenceChangeListener")
    }

    override fun setTheme(resid: Int) {
        Log.d(TAG, "$childClassName.setTheme()")
        if (!loadedFromSharedPreferences) {
            Log.d(TAG, "$childClassName.setTheme(). Loading settings from sharedPreferences")
            loadSettingsFromSharedPreferences()
            loadedFromSharedPreferences = true
            Log.d(TAG, "$childClassName.setTheme(). Settings successfully loaded")
        }
        super.setTheme(currentTheme)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Log.d(TAG, "$childClassName.onSharedPreferenceChanged()")
        if (sharedPreferences == null)
            return
        when (key) {
            Constants.KEY_THEME -> changeTheme(sharedPreferences, key)
            Constants.KEY_MODEL -> changeModel(sharedPreferences, key)
            Constants.KEY_SORT -> changeSort(sharedPreferences, key)
            Constants.KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD -> changeShowWelcomePage(sharedPreferences, key)
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
        Log.d(TAG, "$childClassName.onThemeChanged(). Theme changed to $newTheme")
    }

    protected open fun onModelChanged(newModel: String) {
        Log.d(TAG, "$childClassName.onModelChanged(). Model changed to $newModel")
    }

    protected open fun onSortChanged(newSort: String) {
        Log.d(TAG, "$childClassName.onSortChanged(). Sort changed to $newSort")
    }

    protected open fun onShowWelcomePageChanged(showWelcomePage: Boolean) {
        Log.d(TAG, "$childClassName.onShowWelcomePageChanged(). ShowWelcomePage changed to $showWelcomePage")
    }

    private fun getTheme(themeType: String): Int = when (themeType) {
        Constants.THEME_LIGHT -> Constants.THEME_LIGHT_ID
        Constants.THEME_DARK -> Constants.THEME_DARK_ID
        else -> throw IllegalArgumentException("$childClassName.getTheme(). Unknown theme type")
    }

    private fun loadThemeFromSharedPreferences(sharedPreferences: SharedPreferences, key: String): Int {
        val newThemeType = sharedPreferences.getString(key, Constants.THEME_LIGHT)!!
        return getTheme(newThemeType)
    }

    private fun loadModelFromSharedPreferences(sharedPreferences: SharedPreferences, key: String): String =
        sharedPreferences.getString(key, Constants.MODEL_DEFAULT)!!

    private fun loadSortFromSharedPreferences(sharedPreferences: SharedPreferences, key: String): String =
        sharedPreferences.getString(key, Constants.SORT_DEFAULT)!!

    private fun loadShowWelcomePageFromSharedPreferences(sharedPreferences: SharedPreferences, key: String): Boolean =
        sharedPreferences.getBoolean(key, true)

    private fun loadSettingsFromSharedPreferences() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        currentTheme = loadThemeFromSharedPreferences(sharedPreferences, Constants.KEY_THEME)
        currentModel = loadModelFromSharedPreferences(sharedPreferences, Constants.KEY_MODEL)
        currentSort = loadSortFromSharedPreferences(sharedPreferences, Constants.KEY_SORT)
        showWelcomePage = loadShowWelcomePageFromSharedPreferences(
            sharedPreferences,
            Constants.KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD
        )
    }

    companion object {
        private const val TAG = "BaseActivity"

        private var loadedFromSharedPreferences: Boolean = false
        private var currentTheme: Int = Constants.THEME_DEFAULT_ID
        private var currentModel: String = Constants.MODEL_DEFAULT
        private var currentSort: String = Constants.SORT_DEFAULT
        private var showWelcomePage: Boolean = true
    }
}
