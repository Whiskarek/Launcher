package by.whiskarek.yandexlauncher.activity.welcome

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.activity.welcome.fragments.ModelFragment
import by.whiskarek.yandexlauncher.activity.welcome.fragments.SimpleFragment
import by.whiskarek.yandexlauncher.activity.welcome.fragments.ThemeFragment

class SimpleFragmentAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentList: MutableList<Fragment>

    init {
        val welcomeFragment: Fragment = SimpleFragment.instance(R.layout.activity_welcome_page_welcome_fragment)
        val aboutFragment: Fragment = SimpleFragment.instance(R.layout.activity_welcome_page_about_fragment)
        fragmentList = mutableListOf(welcomeFragment, aboutFragment, ThemeFragment(), ModelFragment())
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        fragmentList[position] = super.instantiateItem(container, position) as Fragment
        return fragmentList[position]
    }

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]
}
