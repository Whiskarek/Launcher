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
import com.microsoft.appcenter.distribute.Distribute
import io.fabric.sdk.android.Fabric

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        Distribute.setListener(AppCenterDistributeListener())
        AppCenter.start(application, BuildConfig.APPCENTER_KEY,
                Analytics::class.java, Crashes::class.java, Distribute::class.java)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.github).setOnClickListener {
            val openGitHubPageIntent = Intent(Intent.ACTION_VIEW)
            openGitHubPageIntent.data = Uri.parse("https://github.com/Whiskarek")
            startActivity(openGitHubPageIntent)
        }
    }
}
