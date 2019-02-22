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

class ModelFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_welcome_page_model_fragment, container, false)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val initModel = sharedPreferences.getString(PreferenceConstants.KEY_MODEL, PreferenceConstants.MODEL_DEFAULT)
        when (initModel) {
            PreferenceConstants.MODEL_DEFAULT ->
                view.findViewById<AppCompatRadioButton>(R.id.rb_default).isChecked = true
            PreferenceConstants.MODEL_SOLID ->
                view.findViewById<AppCompatRadioButton>(R.id.rb_solid).isChecked = true
        }
        view.findViewById<RadioGroup>(R.id.rb_model).setOnCheckedChangeListener {
                _, checkedId ->
            when (checkedId) {
                R.id.rb_default ->
                    sharedPreferences
                        .edit()
                        .putString(PreferenceConstants.KEY_MODEL, PreferenceConstants.MODEL_DEFAULT)
                        .apply()
                R.id.rb_solid ->
                    sharedPreferences
                        .edit()
                        .putString(PreferenceConstants.KEY_MODEL, PreferenceConstants.MODEL_SOLID)
                        .apply()
            }
        }
        return view
    }
}
