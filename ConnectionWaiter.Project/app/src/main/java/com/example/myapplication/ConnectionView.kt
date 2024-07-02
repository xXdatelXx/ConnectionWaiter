package com.example.myapplication

import android.content.Context
import android.media.RingtoneManager
import android.os.VibrationEffect
import android.os.VibrationEffect.createOneShot
import android.os.Vibrator
import android.widget.Toast
//TODO: cache to fields
class ConnectionView(private val context: Context) {
    fun connect(speed: Int) {
        showToast(speed)
        vibrate()
        playSound()
    }

    private fun vibrate() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    }

    private fun playSound() {
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(context.applicationContext, notification)
        r.play()
    }

    private fun showToast(speed: Int) {
        Toast.makeText(context, "Connection speed: $speed kb/s", Toast.LENGTH_LONG).show()
    }
}
