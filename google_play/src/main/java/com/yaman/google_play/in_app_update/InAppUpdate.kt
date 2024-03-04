package com.yaman.google_play.in_app_update

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability

class InAppUpdate(private val activity: AppCompatActivity, val installStatus: (installState: InstallState) -> Unit) {

    private var appUpdateManager: AppUpdateManager? = null
    private val appUpdateType = AppUpdateType.FLEXIBLE

    init {
        appUpdateManager = AppUpdateManagerFactory.create(activity)
    }

    private fun checkForAppUpdate() {

        appUpdateManager?.registerListener(installStateUpdatedListener)

        // Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager?.appUpdateInfo

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask?.addOnSuccessListener { appUpdateInfo ->

            val isUpdateAvailable = appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            val isUpdateAllowed = appUpdateInfo.isUpdateTypeAllowed(appUpdateType)

            if (isUpdateAvailable && isUpdateAllowed) {
                startUpdateFlow(appUpdateManager, appUpdateInfo)
            }
        }
    }

    private fun startUpdateFlow(appUpdateManager: AppUpdateManager?, appUpdateInfo: AppUpdateInfo) {
        try {
            appUpdateManager?.startUpdateFlowForResult(
                appUpdateInfo,
                appUpdateType,
                activity,
                REQUEST_CODE_UPDATE_APP
            )
        } catch (e: Exception) {
            Log.e(TAG, "Method: startUpdateFlow: Error occurred:- " +  e.localizedMessage)
        }
    }


    private val installStateUpdatedListener = InstallStateUpdatedListener { installState ->
        if (installState.installStatus() == InstallStatus.DOWNLOADED) {
            // The update has been downloaded, and the app is ready to be updated.
            // You can show a notification to the user to complete the update.
            installStatus(installState)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int) {

        if (requestCode == REQUEST_CODE_UPDATE_APP) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    Toast.makeText(activity, "App update is in progress.", Toast.LENGTH_LONG).show()
                }

                Activity.RESULT_CANCELED -> {
                    Toast.makeText(activity, "Update cancelled by the user.", Toast.LENGTH_LONG).show()
                }

                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    checkForAppUpdate()
                }
            }
        }

    }

    // Displays the snackbar notification and call to action.
    fun popupSnackbarForCompleteUpdate(view: View) {
        Snackbar.make(
            view,
            "An update has just been downloaded.",
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("RESTART") { appUpdateManager?.completeUpdate() }
            show()
        }
    }

    fun onDestroy() {
        appUpdateManager?.unregisterListener(installStateUpdatedListener)
    }

    companion object {
        private const val TAG = "InAppUpdate"
        private const val REQUEST_CODE_UPDATE_APP = 123
    }

}