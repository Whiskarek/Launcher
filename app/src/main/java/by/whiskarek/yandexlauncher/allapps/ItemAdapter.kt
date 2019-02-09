package by.whiskarek.yandexlauncher.allapps

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.whiskarek.yandexlauncher.R
import java.util.Random

class ItemAdapter(private var inflationType: InflationType): RecyclerView.Adapter<ItemViewHolder>() {
    private val random = Random()
    private val colorMap = mutableMapOf<Int, Int>()

    enum class InflationType {
        LIST,
        GRID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = if (viewType == R.layout.grid_item)
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = 1000

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getColor(position))
    }

    private fun getColor(position: Int): Int {
        var color = colorMap[position]

        if(color == null) {
            color = Color.rgb(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
            )
            colorMap[position] = color
        }

        return color
    }

    fun updateInflationType(inflationType: InflationType) {
        this.inflationType = inflationType
    }

    override fun getItemViewType(position: Int): Int {
        return if (inflationType == InflationType.GRID)
            R.layout.grid_item
        else
            R.layout.list_item
    }
}