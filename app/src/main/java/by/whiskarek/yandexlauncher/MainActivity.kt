package by.whiskarek.yandexlauncher

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.github).setOnClickListener {
            val openGitHubPageIntent = Intent(Intent.ACTION_VIEW)
            openGitHubPageIntent.data = Uri.parse("https://github.com/Whiskarek")
            startActivity(openGitHubPageIntent)
        }
    }
}
