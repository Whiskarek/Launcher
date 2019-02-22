package by.whiskarek.yandexlauncher.activity.welcome

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.core.app.NavUtils
import androidx.viewpager.widget.ViewPager
import by.whiskarek.yandexlauncher.PreferenceConstants
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.activity.BaseActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class WelcomePageActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var adapter: SimpleFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        viewPager = findViewById(R.id.view_pager)
        adapter = SimpleFragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(this::onClick)
        findViewById<TabLayout>(R.id.vp_tabs).setupWithViewPager(viewPager)
    }

    private fun onClick(view: View) {
        if (viewPager.currentItem == adapter.count - 1) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            sharedPreferences
                .edit()
                .putBoolean(PreferenceConstants.KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD, false)
                .apply()
            NavUtils.navigateUpFromSameTask(this)
        }
        if (viewPager.currentItem < adapter.count) {
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem > 0) {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}
