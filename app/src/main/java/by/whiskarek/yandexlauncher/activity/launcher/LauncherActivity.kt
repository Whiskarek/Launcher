package by.whiskarek.yandexlauncher.activity.launcher

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import by.whiskarek.yandexlauncher.AppInfo
import by.whiskarek.yandexlauncher.ArraySorter
import by.whiskarek.yandexlauncher.LauncherApplication
import by.whiskarek.yandexlauncher.activity.BaseActivity
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.activity.profile.ProfileActivity
import by.whiskarek.yandexlauncher.activity.settings.SettingsActivity
import by.whiskarek.yandexlauncher.activity.welcome.WelcomePageActivity

class LauncherActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    private lateinit var navView: NavigationView

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        onPageSelected(position)
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> navView.setCheckedItem(R.id.nav_drawer_launcher_grid)
            1 -> navView.setCheckedItem(R.id.nav_drawer_launcher_list)
        }
    }

    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: SimpleFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        if ((this as BaseActivity).showWelcomePage and LauncherApplication.nextLoad) {
            startActivity(Intent(this, WelcomePageActivity::class.java))
            LauncherApplication.nextLoad = false
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this::onNavigationItemSelected)
        navView.getHeaderView(0).findViewById<LinearLayout>(R.id.header).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        navView.setCheckedItem(R.id.nav_drawer_launcher_grid)

        if (LauncherApplication.itemList.isEmpty()) {
            LauncherApplication.itemList = getInstalledAppList()
        }
        LauncherApplication.itemList =
            ArraySorter(LauncherApplication.itemList, (this as BaseActivity).currentSort).getSortedArray()
        viewPager = findViewById(R.id.vp_items)
        viewPagerAdapter = SimpleFragmentAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(this)
        viewPager.adapter = viewPagerAdapter
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_drawer_launcher_grid -> {
                viewPager.currentItem = 0
            }
            R.id.nav_drawer_launcher_list -> {
                viewPager.currentItem = 1
            }
            R.id.nav_drawer_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getInstalledAppList(): List<AppInfo> {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val appsResolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA)
        val appsInfoList = ArrayList<AppInfo>()
        for (it in appsResolveInfoList) {
            if (it.activityInfo.packageName == packageName)
                continue
            appsInfoList.add(getAppInfo(it))
        }
        return appsInfoList
    }

    private fun getAppInfo(resolveInfo: ResolveInfo): AppInfo {
        val icon = resolveInfo.loadIcon(packageManager)
        val name = resolveInfo.loadLabel(packageManager).toString()
        val launchIntent = Intent(Intent.ACTION_MAIN)
        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        launchIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
        launchIntent.component =
            ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name)
        val infoIntent = Intent(Settings.ACTION_APPLICATION_SETTINGS)
        infoIntent.data = Uri.parse("package:${resolveInfo.activityInfo.packageName}")
        return AppInfo(
            icon,
            name,
            isSystemApp(resolveInfo.activityInfo.packageName),
            launchIntent,
            infoIntent,
            packageManager.getPackageInfo(resolveInfo.activityInfo.packageName, 0).firstInstallTime,
            0
        )
    }

    private fun isSystemApp(packageName: String): Boolean {
        val appInfo = packageManager.getApplicationInfo(packageName, 0)
        val mask = ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP
        return (appInfo.flags and mask) != 0
    }
}
