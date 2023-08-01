package com.arniodev.translator.ui.activity

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.arniodev.translator.R
import com.arniodev.translator.service.ArTranslatorService
import kotlin.concurrent.thread

class NewVersionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_version)

        val service = ArTranslatorService()

        val newVerView = findViewById<TextView>(R.id.newVerView)
        val updateLogView = findViewById<TextView>(R.id.updLogView)
        val currentLanguage = Resources.getSystem().configuration.locales[0].language

        thread {
            val newVerName = service.getVersionName()
            runOnUiThread {
                val newV = StringBuilder()
                newV.append(newVerView.text)
                newV.append(newVerName)
                newVerView.text= newV.toString()
            }
        }

        thread {
            val newLog = service.getChangelog(currentLanguage)
            runOnUiThread {
                updateLogView.text = newLog
            }
        }

    }
}