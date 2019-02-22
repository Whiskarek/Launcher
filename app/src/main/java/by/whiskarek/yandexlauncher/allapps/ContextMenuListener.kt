package by.whiskarek.yandexlauncher.allapps

import android.content.Context
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import by.whiskarek.yandexlauncher.AppInfo
import by.whiskarek.yandexlauncher.R

class ContextMenuListener(
    private val appInfo: AppInfo,
    private val context: Context
) : View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        val system = appInfo.system
        menu?.setHeaderTitle(appInfo.name)
        if (!system) {
            val deleteMenuItem = menu?.add(
                Menu.NONE,
                R.id.context_menu_app_delete,
                Menu.NONE,
                R.string.context_menu_app_delete
            )
            deleteMenuItem?.setOnMenuItemClickListener(this)
        }
        val infoMenuItem = menu?.add(
            Menu.NONE,
            R.id.context_menu_app_info,
            Menu.NONE,
            R.string.context_menu_app_info
        )
        infoMenuItem?.setOnMenuItemClickListener(this)
        val launches = "${context.getString(R.string.context_menu_launches)} ${appInfo.launchAmount}"
        menu?.add(
            Menu.NONE,
            R.id.context_menu_launches,
            Menu.NONE,
            launches
        )
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.context_menu_app_info -> context.startActivity(appInfo.infoIntent)
            R.id.context_menu_app_delete -> context.startActivity(appInfo.deleteIntent)
        }
        return true
    }
}
