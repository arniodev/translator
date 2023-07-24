package com.arniodev.translator.ui.activity

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.arniodev.translator.R
import com.arniodev.translator.service.ArTranslatorService
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import java.util.*
import kotlin.concurrent.thread


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

//        val locale = Locale(Resources.getSystem().configuration.locales.get(0).language)
//        Locale.setDefault(locale)
//        val resources = this.resources
//        val configuration = resources.configuration
//        configuration.setLocale(locale)
//        this.createConfigurationContext(configuration)

        AppCenter.start(
            application, "034c8f4d-7355-4a6c-943f-1f9103961d1d",
            Analytics::class.java, Crashes::class.java
        ) // 加载Microsoft App Center

        val iconMsg = Message()
        iconMsg.what = SHOW_ICON
        handler.sendMessageDelayed(iconMsg,750)

        val id = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        val service = ArTranslatorService()
        thread {
            if(service.checkDevices(id)) {
                val activityMsg = Message()
                activityMsg.what = START_MAIN_ACTIVITY
                handler.sendMessage(activityMsg)
            } else {
                val intent = Intent(this,AccessDeniedActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}