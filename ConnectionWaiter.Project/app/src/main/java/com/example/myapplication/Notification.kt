package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

class Notification(private val context: Context, private val title: String, private val message: String) {
    private val manager : NotificationManager

    init {
        manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            R.string.notification_channel_id.toString(),
            R.string.notification_channel_name.toString(),
            NotificationManager.IMPORTANCE_DEFAULT)

        manager.createNotificationChannel(channel)
    }

    fun send() {
        if(title.isEmpty()) throw Exception("Notification $title is empty")
        if(message.isEmpty()) throw Exception("Notification $message is empty")

        val builder = NotificationCompat.Builder(context, R.string.notification_channel_id.toString())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        manager.notify(1, builder.build())
    }
}