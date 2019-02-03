package by.whiskarek.yandexlauncher

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.crashlytics.android.Crashlytics
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import io.fabric.sdk.android.Fabric

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        AppCenter.start(application, "415c2e43-fe28-4d27-8abd-25be79341c57",
                Analytics::class.java, Crashes::class.java)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.github).setOnClickListener {
            val openGitHubPageIntent = Intent(Intent.ACTION_VIEW)
            openGitHubPageIntent.data = Uri.parse("https://github.com/Whiskarek")
            startActivity(openGitHubPageIntent)
        }
    }
}
