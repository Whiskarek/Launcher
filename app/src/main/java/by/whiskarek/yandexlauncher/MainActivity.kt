package by.whiskarek.yandexlauncher

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.github).setOnClickListener {
            val openGitHubPageIntent = Intent(Intent.ACTION_VIEW)
            openGitHubPageIntent.data = Uri.parse("https://github.com/Whiskarek")
            startActivity(openGitHubPageIntent)
        }
        val imageBitmap = BitmapFactory.decodeResource(resources, R.drawable.photo)
        val roundedImageDrawable = RoundedBitmapDrawableFactory.create(resources, imageBitmap)
        roundedImageDrawable.isCircular = true
        civ_photo.setImageDrawable(roundedImageDrawable)
    }
}
