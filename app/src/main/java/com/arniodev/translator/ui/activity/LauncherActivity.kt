package com.arniodev.translator.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.arniodev.translator.R

class LauncherActivity : AppCompatActivity() {

    private val SHOW_ICON = 0x0000
    private val START_MAIN_ACTIVITY = 0x0002

    private val handler = object : Handler(Looper.getMainLooper()){

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                SHOW_ICON -> {
                    val launcherIconView = findViewById<View>(R.id.LauncherIcon)
                    launcherIconView.visibility = ViewGroup.VISIBLE
                }

                START_MAIN_ACTIVITY -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        val iconMsg = Message()
        iconMsg.what = SHOW_ICON
        handler.sendMessageDelayed(iconMsg,750)
        val activityMsg = Message()
        activityMsg.what = START_MAIN_ACTIVITY
        handler.sendMessageDelayed(activityMsg,2500)
    }
}