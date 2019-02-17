package by.whiskarek.yandexlauncher

import android.content.Intent
import android.graphics.drawable.Drawable

data class AppInfo(
    val icon: Drawable,
    val name: String,
    val launchIntent: Intent
)
