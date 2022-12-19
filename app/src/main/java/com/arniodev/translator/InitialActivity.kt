package com.arniodev.translator

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

class InitialActivity : AppCompatActivity() {

    private val SHOW_EXAMPLE = 0x0004
    private val SHOW_TAP_BUTTON = 0x0002
    private val  SHOW_BACK_BUTTON = 0x0003
    private lateinit var _CONTEXT: Context

    private val handler = object : Handler(Looper.getMainLooper()){

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            val exampleView = findViewById<View>(R.id.translation_example)
            val tapView = findViewById<View>(R.id.tap_to_start)
            val backBtnView = findViewById<View>(R.id.back_btn_layout)

            when (msg.what) {
                SHOW_EXAMPLE -> {
                    exampleView.visibility = ViewGroup.VISIBLE
                }
                SHOW_TAP_BUTTON -> {
                    tapView.setOnClickListener {
                        val intent = Intent(_CONTEXT,PrivacyActivity::class.java)
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@InitialActivity,findViewById<View>(R.id.init_activity_view),"TapToStart").toBundle())

                        tapView.visibility = ViewGroup.GONE
                        exampleView.visibility = ViewGroup.GONE
                    }
                    tapView.visibility = ViewGroup.VISIBLE
                }
                SHOW_BACK_BUTTON -> {
                    backBtnView.setOnClickListener {
                        finish()
                    }
                    backBtnView.visibility = ViewGroup.VISIBLE
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        if(!::_CONTEXT.isInitialized) {
            _CONTEXT = this
        }

        val exampleMsg = Message()
        exampleMsg.what = SHOW_EXAMPLE
        handler.sendMessageDelayed(exampleMsg,500)

        val tapButtonMsg = Message()
        tapButtonMsg.what = SHOW_TAP_BUTTON
        handler.sendMessageDelayed(tapButtonMsg,1500)

        val backButtonMsg = Message()
        backButtonMsg.what = SHOW_BACK_BUTTON
        handler.sendMessageDelayed(backButtonMsg,1550)
    }

    override fun onResume() {
        super.onResume()

        val exampleMsg = Message()
        exampleMsg.what = SHOW_EXAMPLE
        handler.sendMessageDelayed(exampleMsg,100)

        val tapButtonMsg = Message()
        tapButtonMsg.what = SHOW_TAP_BUTTON
        handler.sendMessageDelayed(tapButtonMsg,500)
    }
}