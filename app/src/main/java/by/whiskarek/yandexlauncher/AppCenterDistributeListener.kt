package by.whiskarek.yandexlauncher

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.microsoft.appcenter.distribute.Distribute
import com.microsoft.appcenter.distribute.DistributeListener
import com.microsoft.appcenter.distribute.ReleaseDetails
import com.microsoft.appcenter.distribute.UpdateAction

class AppCenterDistributeListener : DistributeListener {

    override fun onReleaseAvailable(activity: Activity, releaseDetails: ReleaseDetails): Boolean {
        val versionName = releaseDetails.shortVersion
        val releaseNotes = releaseDetails.releaseNotes
        val dialogBuilder = AlertDialog.Builder(activity)

        dialogBuilder.setTitle(
                activity.applicationContext.resources
                        .getString(R.string.appcenter_distribute_version_xxx_available)
                        .format(versionName)
        )
        dialogBuilder.setMessage(releaseNotes)
        dialogBuilder.setPositiveButton(R.string.appcenter_distribute_update_dialog_download) {
            _, _ -> Distribute.notifyUpdateAction(UpdateAction.UPDATE)
        }
        if (!releaseDetails.isMandatoryUpdate) {
            dialogBuilder.setNegativeButton(R.string.appcenter_distribute_update_dialog_postpone) {
                _, _ -> Distribute.notifyUpdateAction(UpdateAction.POSTPONE)
            }
        }
        dialogBuilder.setCancelable(false)
        dialogBuilder.setOnCancelListener {
            it.cancel()
        }
        dialogBuilder.create().show()

        return true
    }
}