package com.arniodev.translator.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.arniodev.translator.BuildConfig
import com.arniodev.translator.R
import java.util.*

class AboutActivity : AppCompatActivity() {

    var clickCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val ArTranslatorIconView = findViewById<ImageView>(R.id.icon_view)
        val logoView = findViewById<ImageView>(R.id.logo)
        val checkUpdateView = findViewById<View>(R.id.checkUpdateBox)
        val contactUsView = findViewById<View>(R.id.contactUsView)
        val contributorView = findViewById<View>(R.id.contributorBox)

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
//            TODO("SWITCH LANGUAGE TO CANTONESE WHEN clickCounter > 15")
//            if(clickCounter > 15) {
//                val locale = Locale("zh", "GD")
//                //Locale.setDefault(locale)
//                val resources = this.resources
//                val configuration = resources.configuration
//                configuration.setLocale(locale)
//                this.createConfigurationContext(configuration)
//                //MainActivity().recreate()
//                finish()
//            }
        }

        contactUsView.setOnClickListener {
            startActivity(Intent(this,QQGroupActivity::class.java))
        }

        checkUpdateView.setOnClickListener {
            Toast.makeText(this,R.string.service_unavailable,Toast.LENGTH_SHORT).show()
        }

        contributorView.setOnClickListener {
            startActivity(Intent(this,ContributorActivity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // startActivity(Intent(this,MainActivity::class.java))
    }

}