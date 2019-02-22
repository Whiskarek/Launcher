package by.whiskarek.yandexlauncher

class PreferenceConstants {
    companion object {
        const val KEY_THEME = "pref_theme"
        const val KEY_MODEL = "pref_model"
        const val KEY_SORT = "pref_sort"
        const val KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD = "pref_show_welcome_page_on_next_load"

        const val THEME_LIGHT = "light"
        const val THEME_LIGHT_ID = R.style.AppThemeLight
        const val THEME_DARK = "dark"
        const val THEME_DARK_ID = R.style.AppThemeDark
        const val THEME_DEFAULT = THEME_LIGHT
        const val THEME_DEFAULT_ID = THEME_LIGHT_ID

        const val MODEL_DEFAULT = "default"
        const val MODEL_SOLID = "solid"

        const val SORT_ALPHABET_HIGH = "alphabet_high"
        const val SORT_ALPHABET_LOW = "alphabet_low"
        const val SORT_INSTALL_DATE_HIGH = "install_date_high"
        const val SORT_INSTALL_DATE_LOW = "install_date_low"
        const val SORT_LAUNCH_AMOUNT_HIGH = "launch_amount_high"
        const val SORT_LAUNCH_AMOUNT_LOW = "launch_amount_low"
        const val SORT_DEFAULT = SORT_ALPHABET_HIGH
    }
}
