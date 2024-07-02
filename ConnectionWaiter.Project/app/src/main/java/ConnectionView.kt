package com.example.myapplication

import android.content.Context

class ConnectionView(private val context: Context) {
    fun connect(speed: Int) {
        Notification(
            context,
            title = "Connected",
            message = "Connection speed: $speed kb/s"
        ).send()
    }
}