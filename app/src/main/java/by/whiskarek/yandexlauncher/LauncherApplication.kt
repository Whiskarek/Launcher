package by.whiskarek.yandexlauncher

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute
import io.fabric.sdk.android.Fabric

class LauncherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Distribute.setEnabled(true)
        Distribute.setListener(AppCenterDistributeListener())
        AppCenter.start(
                this,
                BuildConfig.APPCENTER_KEY,
                Analytics::class.java,
                Crashes::class.java,
                Distribute::class.java
        )
        Fabric.with(this, Crashlytics())
    }
}