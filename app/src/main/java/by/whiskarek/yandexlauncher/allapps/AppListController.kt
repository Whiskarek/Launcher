package by.whiskarek.yandexlauncher.allapps

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.whiskarek.yandexlauncher.R

class AppListController(private val appList: RecyclerView) {

    init {
        appList.adapter = ItemAdapter(ItemAdapter.InflationType.GRID)
        appList.layoutManager = GridLayoutManager(appList.context, 5)
        val offset = appList.context.resources.getDimensionPixelOffset(R.dimen.app_list_item_offset)
        appList.addItemDecoration(GridItemDecoration(offset))
        appList.setHasFixedSize(true)
    }

    fun setGridLayout() {
        val scrollPosition = getScrollPosition()
        appList.layoutManager = GridLayoutManager(appList.context, 5)
        (appList.adapter as ItemAdapter).updateInflationType(ItemAdapter.InflationType.GRID)
        appList.scrollToPosition(scrollPosition)
    }

    fun setListLayout() {
        val scrollPosition = getScrollPosition()
        appList.layoutManager = LinearLayoutManager(appList.context)
        (appList.adapter as ItemAdapter).updateInflationType(ItemAdapter.InflationType.LIST)
        appList.scrollToPosition(scrollPosition)
    }

    private fun getScrollPosition(): Int =
            (appList.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
}