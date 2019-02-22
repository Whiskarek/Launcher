package by.whiskarek.yandexlauncher.activity.launcher.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.whiskarek.yandexlauncher.LauncherApplication
import by.whiskarek.yandexlauncher.PreferenceConstants
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.activity.BaseActivity
import by.whiskarek.yandexlauncher.allapps.GridItemDecoration
import by.whiskarek.yandexlauncher.allapps.ItemGridAdapter
import java.lang.IllegalArgumentException

class GridRecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: ItemGridAdapter
    private lateinit var recyclerViewLayoutManager: GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_launcher_recycler_view_fragment, container, false)
        val spanCount = when ((activity as BaseActivity).currentModel) {
            PreferenceConstants.MODEL_DEFAULT -> resources.getInteger(R.integer.model_default)
            PreferenceConstants.MODEL_SOLID -> resources.getInteger(R.integer.model_solid)
            else -> throw IllegalArgumentException()
        }
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerViewAdapter = ItemGridAdapter(view.context, LauncherApplication.itemList)
        recyclerViewLayoutManager = GridLayoutManager(view.context, spanCount)
        recyclerView.layoutManager = recyclerViewLayoutManager
        recyclerView.adapter = recyclerViewAdapter
        val offset = resources.getDimensionPixelOffset(R.dimen.item_margin)
        recyclerView.addItemDecoration(GridItemDecoration(offset))
        recyclerView.hasFixedSize()
        return view
    }
}