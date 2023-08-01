package com.arniodev.translator.ui.activity

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.arniodev.translator.R
import com.arniodev.translator.service.ArTranslatorService
import com.github.ybq.android.spinkit.style.Wave
import kotlin.concurrent.thread

class NewVersionActivity : AppCompatActivity() {

    private var callbackCount = 0
    private lateinit var loadingView : View
    private lateinit var infoView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_version)

        val service = ArTranslatorService()

        val newVerView = findViewById<TextView>(R.id.newVerView)
        val updateLogView = findViewById<TextView>(R.id.updLogView)
        val pBarView = findViewById<ProgressBar>(R.id.pBar)
        val currentLanguage = Resources.getSystem().configuration.locales[0].language

        loadingView = findViewById(R.id.loadingView)
        infoView = findViewById(R.id.info_view)

        pBarView.indeterminateDrawable = Wave()

        thread {
            val newVerName = service.getVersionName()
            runOnUiThread {
                val newV = StringBuilder()
                newV.append(newVerView.text)
                newV.append(newVerName)
                newVerView.text= newV.toString()
            }
            callback()
        }

        thread {
            val newLog = service.getChangelog(currentLanguage)
            runOnUiThread {
                updateLogView.text = newLog
            }
            callback()
        }

    }

    private fun callback() {
        callbackCount++
        if(callbackCount > 1) {
            runOnUiThread {
                loadingView.visibility = ViewGroup.GONE
                infoView.visibility = ViewGroup.VISIBLE
            }
        }
    }
}