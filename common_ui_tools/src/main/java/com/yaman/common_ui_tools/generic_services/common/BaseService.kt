package com.yaman.common_ui_tools.generic_services.common

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.yaman.common_ui_tools.R
import java.io.Serializable

abstract class BaseService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    abstract fun onStartService()
    abstract fun onStopService()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Action.START.toString() -> {
                val notification = createNotification(intent)
                start(notification)
                onStartService()
            }

            Action.STOP.toString() -> {
                onStopService()
                stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(notification: Notification) {
        startForeground(1, notification)
    }

    open fun createNotification(intent: Intent): Notification {
        //Get Notification Data
        val notificationData: NotificationData
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (intent.getSerializableExtra(
                    NOTIFICATION_DATA,
                    NotificationData::class.java
                ) == null
            ) {
                throw RuntimeException(TAG + "NOTIFICATION_DATA of NotificationData::class.java is required.")
            } else {
                notificationData =
                    intent.getSerializableExtra(NOTIFICATION_DATA, NotificationData::class.java)!!
            }
        } else {
            if (intent.getSerializableExtra(NOTIFICATION_DATA) == null) {
                throw RuntimeException(TAG + "NOTIFICATION_DATA of NotificationData::class.java is required.")
            } else {
                notificationData =
                    intent.getSerializableExtra(NOTIFICATION_DATA)!! as NotificationData
            }
        }

        //Create Notification Channel. if Above Oreo - 26
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                notificationData.channelId,
                notificationData.channelName,
                notificationData.importance
            )

            //Get System Service from OS
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }


        return NotificationCompat.Builder(this, notificationData.channelId)
            .setSmallIcon(notificationData.smallIcon)
            .setContentTitle(notificationData.contentTitle)
            .setContentText(notificationData.contentText)
            .setAutoCancel(notificationData.autoCancel)
            .build()
    }


    companion object {
        private const val TAG: String = "RunningService: "
        const val NOTIFICATION_DATA = "NotificationData"
    }

    /*
    Action For Service
    * */
    enum class Action {
        START,
        STOP
    }


    data class NotificationData(
        var channelId: String,
        var channelName: String = "Default Channel Name",
        var importance: Int = NotificationManager.IMPORTANCE_HIGH,
        var smallIcon: Int = R.drawable.ic_notification_icon,
        var contentTitle: String? = "Default Title",
        var contentText: String? = "Default Text",
        var autoCancel: Boolean = false,
    ) : Serializable

}