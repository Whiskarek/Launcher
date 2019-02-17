package by.whiskarek.yandexlauncher.allapps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.whiskarek.yandexlauncher.AppInfo
import by.whiskarek.yandexlauncher.R

class ItemGridAdapter(
    private val context: Context,
    private val appList: List<AppInfo>
) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun getItemCount(): Int = appList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(appList[position])
        holder.setOnClickListener(View.OnClickListener {
            context.startActivity(appList[position].launchIntent)
        })
    }
}