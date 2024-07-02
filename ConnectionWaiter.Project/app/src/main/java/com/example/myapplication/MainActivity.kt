package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constructConnectButton()
    }

    private fun constructConnectButton() {
        val connection = Intent(this, ConnectionService::class.java)
        val awaitConnectionButton = findViewById<Button>(R.id.AwaitConnection)

        awaitConnectionButton.setOnClickListener { startService(connection) }
    }
}