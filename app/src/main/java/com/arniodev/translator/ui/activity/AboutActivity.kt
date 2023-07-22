package com.arniodev.translator.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.arniodev.translator.BuildConfig
import com.arniodev.translator.R

class AboutActivity : AppCompatActivity() {

    var clickCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val ArTranslatorIconView = findViewById<ImageView>(R.id.icon_view)
        val logoView = findViewById<ImageView>(R.id.logo)
        val checkUpdateView = findViewById<View>(R.id.checkUpdateBox)
        val contactUsView = findViewById<View>(R.id.contactUsView)
        val versionView = findViewById<TextView>(R.id.versionView)
        versionView.text = BuildConfig.VERSION_NAME

        val backBtnView = findViewById<View>(R.id.back_btn)
        backBtnView.setOnClickListener {
            finish()
        }

        ArTranslatorIconView.setOnClickListener {
            clickCounter++
            if (clickCounter == 10) {
                logoView.setImageResource(R.drawable.arniodev_dark)
            }
            if(clickCounter > 15) {

                TODO("SET LANGUAGE TO CANTONESE")
            }
        }

        contactUsView.setOnClickListener {
            Toast.makeText(this,R.string.service_unavailable,Toast.LENGTH_SHORT).show()
        }

        checkUpdateView.setOnClickListener {
            Toast.makeText(this,R.string.service_unavailable,Toast.LENGTH_SHORT).show()
        }

    }
}