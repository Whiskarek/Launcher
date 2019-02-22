package by.whiskarek.yandexlauncher

import android.content.Intent
import android.graphics.drawable.Drawable

data class AppInfo(
    val icon: Drawable,
    val name: String,
    val system: Boolean,
    val launchIntent: Intent,
    val infoIntent: Intent,
    val deleteIntent: Intent,
    val installTime: Long,
    var launchAmount: Int
)
