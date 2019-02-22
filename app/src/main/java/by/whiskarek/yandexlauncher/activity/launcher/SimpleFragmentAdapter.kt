package by.whiskarek.yandexlauncher.activity.launcher

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import by.whiskarek.yandexlauncher.activity.launcher.fragments.GridRecyclerViewFragment
import by.whiskarek.yandexlauncher.activity.launcher.fragments.ListRecyclerViewFragment

class SimpleFragmentAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentList: MutableList<Fragment> =
        mutableListOf(GridRecyclerViewFragment(), ListRecyclerViewFragment())

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        fragmentList[position] = super.instantiateItem(container, position) as Fragment
        return fragmentList[position]
    }

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]
}
