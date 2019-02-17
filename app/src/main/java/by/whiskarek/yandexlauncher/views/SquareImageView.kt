package by.whiskarek.yandexlauncher.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import kotlin.math.min

class SquareImageView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val min = min(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(min, min)
    }
}