package by.whiskarek.yandexlauncher.activity.settings

import android.os.Bundle
import by.whiskarek.yandexlauncher.R
import androidx.preference.*

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        initSummary(preferenceScreen)
    }

    private fun initSummary(preference: Preference) {
        if (preference is PreferenceGroup) {
            for (i in 0 until preference.preferenceCount) {
                initSummary(preference.getPreference(i))
            }
        } else {
            setPreferenceSummary(preference)
        }
    }

    private fun setPreferenceSummary(preference: Preference) {
        if (preference is ListPreference) {
            preference.setSummaryProvider(ListPreference.SimpleSummaryProvider.getInstance())
        } else if (preference is EditTextPreference) {
            preference.setSummaryProvider(EditTextPreference.SimpleSummaryProvider.getInstance())
        }
    }
}