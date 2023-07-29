package com.arniodev.translator.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.arniodev.translator.R
import com.arniodev.translator.service.ArTranslatorService
import com.github.ybq.android.spinkit.style.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class CheckUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_update)
        val service = ArTranslatorService()
        val pBarView = findViewById<ProgressBar>(R.id.pBar)
        val statusView = findViewById<TextView>(R.id.status)
        val doneView = findViewById<ImageView>(R.id.doneView)
        pBarView.indeterminateDrawable = Wave()

        thread {
            if(service.checkUpdate()){
                Log.d("ArT","UPDATE")
            } else {
                sleep(750)
                runOnUiThread {
                    statusView.text = getString(R.string.up2date)
                    pBarView.visibility = ViewGroup.GONE
                    doneView.visibility = ViewGroup.VISIBLE
                }
            }
        }
    }
}