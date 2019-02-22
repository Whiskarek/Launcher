package by.whiskarek.yandexlauncher.views

import android.content.Context
import android.graphics.PorterDuffXfermode
import android.graphics.PorterDuff
import android.graphics.Color
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val min = Math.min(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(min, min)
    }

    override fun onDraw(canvas: Canvas) {
        if (drawable == null)
            return
        if (width == 0 || height == 0) {
            return
        }
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val roundBitmap = getRoundedCroppedBitmap(bitmap, minOf(width, height))
        canvas.drawBitmap(roundBitmap, 0f, 0f, null)
    }

    companion object {
        fun getRoundedCroppedBitmap(bitmap: Bitmap, radius: Int): Bitmap {
            val finalBitmap: Bitmap
            finalBitmap = when {
                bitmap.width < bitmap.height -> {
                    val offset = (bitmap.height - bitmap.width) / 2
                    val newBitmap = Bitmap.createBitmap(bitmap, 0, offset, bitmap.width, bitmap.width)
                    Bitmap.createScaledBitmap(newBitmap, radius, radius, false)
                }
                bitmap.width > bitmap.height -> {
                    val offset = (bitmap.width - bitmap.height) / 2
                    val newBitmap = Bitmap.createBitmap(bitmap, offset, 0, bitmap.height, bitmap.height)
                    Bitmap.createScaledBitmap(newBitmap, radius, radius, false)
                }
                else -> bitmap
            }
            val output = Bitmap.createBitmap(
                finalBitmap.width,
                finalBitmap.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(output)
            val paint = Paint()
            val rect = Rect(
                0, 0,
                finalBitmap.width,
                finalBitmap.height
            )

            paint.isAntiAlias = true
            paint.isFilterBitmap = true
            paint.isDither = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = Color.parseColor("#BAB399")
            canvas.drawCircle(
                finalBitmap.width / 2 + 0.7f,
                finalBitmap.height / 2 + 0.7f,
                finalBitmap.width / 2 + 0.1f, paint
            )
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(finalBitmap, rect, rect, paint)
            return output
        }
    }
}
