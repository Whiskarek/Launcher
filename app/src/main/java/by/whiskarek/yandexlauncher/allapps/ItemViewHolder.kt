package by.whiskarek.yandexlauncher.allapps

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.whiskarek.yandexlauncher.AppInfo
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.views.SquareImageView

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val icon: SquareImageView = itemView.findViewById(R.id.item_icon)
    private val name: TextView = itemView.findViewById(R.id.item_name)

    fun bind(app: AppInfo) {
        icon.setImageDrawable(app.icon)
        name.text = app.name
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        itemView.setOnClickListener(listener)
    }
}