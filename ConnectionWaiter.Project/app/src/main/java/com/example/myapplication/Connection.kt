package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import java.util.concurrent.Executors
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class Connection(
    private val context: Context,
    private val minimalSpeed: Int,
    private val connectionRetryInterval: Duration = 1.seconds,
    private val view: ConnectionView = ConnectionView(context)
) {
    private val executeService = Executors.newSingleThreadExecutor()

    // (Connection download speed + upload speed) / 2
    private val speed: Int
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nc = cm.getNetworkCapabilities(cm.activeNetwork) ?: return 0

            val downSpeed = nc.linkDownstreamBandwidthKbps
            val upSpeed = nc.linkUpstreamBandwidthKbps

            return (downSpeed + upSpeed) / 2
        }

    // Wait until a connection speed < minimalSpeed
    fun connect() {
        if (minimalSpeed <= 0) throw Exception("$minimalSpeed must be greater than 0")
        if (connectionRetryInterval.inWholeMilliseconds <= 0) throw Exception("$connectionRetryInterval must be greater than 0")

        executeService.execute {
            while (speed < minimalSpeed)
                Thread.sleep(connectionRetryInterval.inWholeMilliseconds)
        }

        view.connect(speed)
    }
}
