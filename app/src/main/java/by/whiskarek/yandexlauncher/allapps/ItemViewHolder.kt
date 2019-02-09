package by.whiskarek.yandexlauncher.allapps

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.views.SquareView

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val icon: SquareView = itemView.findViewById(R.id.item_icon)
    private val name: TextView = itemView.findViewById(R.id.item_name)

    fun bind(color: Int) {
        icon.setBackgroundColor(color)
        name.text = String.format("#%06X", (0xFFFFFF and color))
    }
}