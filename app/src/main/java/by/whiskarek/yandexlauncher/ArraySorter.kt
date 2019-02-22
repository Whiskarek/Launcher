package by.whiskarek.yandexlauncher

import java.lang.IllegalArgumentException

class ArraySorter(
    private val itemList: List<AppInfo>,
    private val sortType: String) {

    fun getSortedArray(): List<AppInfo> {
        val comparator = getComparator()
        return itemList.sortedWith(Comparator(comparator))
    }

    private fun getComparator(): (AppInfo?, AppInfo?) -> Int = when (sortType) {
        PreferenceConstants.SORT_ALPHABET_HIGH -> ArraySorter.Companion::compareNamesHigh
        PreferenceConstants.SORT_ALPHABET_LOW -> ArraySorter.Companion::compareNamesLow
        PreferenceConstants.SORT_INSTALL_DATE_HIGH -> ArraySorter.Companion::installDateHigh
        PreferenceConstants.SORT_INSTALL_DATE_LOW -> ArraySorter.Companion::installDateLow
        PreferenceConstants.SORT_LAUNCH_AMOUNT_HIGH -> ArraySorter.Companion::launchAmountHigh
        PreferenceConstants.SORT_LAUNCH_AMOUNT_LOW -> ArraySorter.Companion::launchAmountLow
        else -> throw IllegalArgumentException("Unknown sort type")
    }

    companion object {
        fun compareNamesHigh(app1: AppInfo?, app2: AppInfo?): Int = app1!!.name.compareTo(app2!!.name)
        fun compareNamesLow(app1: AppInfo?, app2: AppInfo?): Int = -compareNamesHigh(app1, app2)
        fun installDateHigh(app1: AppInfo?, app2: AppInfo?): Int = app1!!.installTime.compareTo(app2!!.installTime)
        fun installDateLow(app1: AppInfo?, app2: AppInfo?): Int = -installDateHigh(app1, app2)
        fun launchAmountHigh(app1: AppInfo?, app2: AppInfo?): Int = app1!!.launchAmount.compareTo(app2!!.launchAmount)
        fun launchAmountLow(app1: AppInfo?, app2: AppInfo?): Int = -launchAmountHigh(app1, app2)
    }

}