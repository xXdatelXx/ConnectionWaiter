package com.example.myapplication

import android.app.Service
import android.content.Intent

// For foreground app work
class ConnectionService : Service() {
    private lateinit var connection : Connection

    override fun onCreate() {
        super.onCreate()
        connection = Connection(this, resources.getInteger(R.integer.minimal_speed))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        connection.connect()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?) = null
}
