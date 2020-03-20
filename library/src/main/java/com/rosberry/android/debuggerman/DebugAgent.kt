package com.rosberry.android.debuggerman

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.rosberry.android.debuggerman.presentation.Debug

/**
 * @author mmikhailov on 20.03.2020.
 */
class DebugAgent : Service() {

    companion object {
        var host: Activity? = null
        var supportedItems = mutableListOf<Debug>()

        internal const val ACTION_OPEN = BuildConfig.LIBRARY_PACKAGE_NAME + ".open_debug"
        private const val ACTION_START = BuildConfig.LIBRARY_PACKAGE_NAME + ".start_agent"

        private const val TAG = "DebugAgent"
        private const val NOTIFICATION_ID = 100
        private const val CHANNEL_ID = "debug dialog"

        fun start(activity: Activity) {
            host = activity
            activity.startService(Intent(activity.applicationContext, DebugAgent::class.java).apply {
                action = ACTION_START
            })
        }

        fun place(action: Debug) {
            supportedItems.add(action)
        }

        fun remove(action: Debug) {
            supportedItems.remove(action)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand::intent:action=${intent?.action}")
        when (intent?.action) {
            ACTION_START -> showNotification()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? = null


    private fun showNotification() {
        if (host == null) {
            Log.w(TAG, "Cannot start debug agent. The host activity was not set.")
            return
        }

        val bakedHost = host!!
        val nm = NotificationManagerCompat.from(bakedHost)
        val intent = Intent(bakedHost, bakedHost::class.java).apply {
            action = ACTION_OPEN
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val contentIntent = PendingIntent.getActivity(bakedHost, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        createNotificationChannel(nm)

        val notification = NotificationCompat.Builder(bakedHost, CHANNEL_ID)
            .setContentTitle("Debug")
            .setContentText("Click to open debug dialog")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .setSmallIcon(R.drawable.ic_bug_report_black_24dp)
            .setContentIntent(contentIntent)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(nm: NotificationManagerCompat) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Debug screen"
            val description = "Debug app"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            channel.description = description

            nm.createNotificationChannel(channel)
        }
    }
}