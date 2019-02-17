package by.whiskarek.yandexlauncher

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.whiskarek.yandexlauncher.allapps.GridItemDecoration
import by.whiskarek.yandexlauncher.allapps.ItemGridAdapter
import by.whiskarek.yandexlauncher.allapps.ItemListAdapter

class LauncherActivity : AppCompatActivity() {

    private lateinit var listItems: RecyclerView
    private lateinit var gridItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val appList = getInstalledAppList()
        listItems = findViewById(R.id.list_items)
        listItems.layoutManager = LinearLayoutManager(this)
        listItems.adapter = ItemListAdapter(this, appList)
        listItems.visibility = View.GONE
        gridItems = findViewById(R.id.grid_items)
        gridItems.layoutManager = GridLayoutManager(this, 5)
        gridItems.adapter = ItemGridAdapter(this, appList)
        val offset = resources.getDimensionPixelOffset(R.dimen.item_margin)
        gridItems.addItemDecoration(GridItemDecoration(offset))
        val dividerItemDecoration = DividerItemDecoration(this, (listItems.layoutManager as LinearLayoutManager).orientation)
        listItems.addItemDecoration(GridItemDecoration(offset))
        listItems.addItemDecoration(dividerItemDecoration)

        navView.setNavigationItemSelectedListener(this::onNavigationItemSelected)
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
                listItems.visibility = View.GONE
                gridItems.visibility = View.VISIBLE
            }
            R.id.nav_drawer_launcher_list -> {
                gridItems.visibility = View.GONE
                listItems.visibility = View.VISIBLE
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
        return  AppInfo(icon, name, launchIntent)
    }
}
