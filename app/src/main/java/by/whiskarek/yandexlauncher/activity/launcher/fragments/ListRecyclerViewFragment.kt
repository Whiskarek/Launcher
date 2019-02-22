package by.whiskarek.yandexlauncher.activity.launcher.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.whiskarek.yandexlauncher.LauncherApplication
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.allapps.GridItemDecoration
import by.whiskarek.yandexlauncher.allapps.ItemListAdapter

class ListRecyclerViewFragment() : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: ItemListAdapter
    private lateinit var recyclerViewLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_launcher_recycler_view_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerViewAdapter = ItemListAdapter(view.context, LauncherApplication.itemList)
        recyclerViewLayoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = recyclerViewLayoutManager
        recyclerView.adapter = recyclerViewAdapter
        val dividerItemDecoration =
            DividerItemDecoration(view.context, (recyclerView.layoutManager as LinearLayoutManager).orientation)
        val offset = resources.getDimensionPixelOffset(R.dimen.item_margin)
        recyclerView.addItemDecoration(GridItemDecoration(offset))
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.hasFixedSize()
        return view
    }
}