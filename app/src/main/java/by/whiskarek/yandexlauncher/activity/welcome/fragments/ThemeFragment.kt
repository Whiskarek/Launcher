package by.whiskarek.yandexlauncher.activity.welcome.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import by.whiskarek.yandexlauncher.PreferenceConstants
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.activity.BaseActivity

class ThemeFragment : Fragment() {

    private lateinit var radioGroup: RadioGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_welcome_page_theme_fragment, container, false)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        when ((activity as BaseActivity).currentTheme) {
            PreferenceConstants.THEME_LIGHT_ID -> view.findViewById<AppCompatRadioButton>(R.id.rb_light).isChecked = true
            PreferenceConstants.THEME_DARK_ID -> view.findViewById<AppCompatRadioButton>(R.id.rb_dark).isChecked = true
        }
        radioGroup = view.findViewById(R.id.rb_theme)
        radioGroup.setOnCheckedChangeListener {
                _, checkedId ->
            when (checkedId) {
                R.id.rb_light -> sharedPreferences
                    .edit()
                    .putString(PreferenceConstants.KEY_THEME, PreferenceConstants.THEME_LIGHT)
                    .apply()
                R.id.rb_dark -> sharedPreferences
                    .edit()
                    .putString(PreferenceConstants.KEY_THEME, PreferenceConstants.THEME_DARK)
                    .apply()
            }
        }
        return view
    }
}
