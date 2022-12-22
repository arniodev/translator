package com.arniodev.translator.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arniodev.translator.R

class MainActivity : AppCompatActivity() {

    private fun configChecker(): Unit {
        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val configured = prefs.getBoolean("configured",false)

        Log.d("ArT",configured.toString())

        if(!configured) {
            val intent = Intent(this, InitialActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configChecker()

        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val engine = prefs?.getString("engine","DeepL")
        val poweredByView = findViewById<View>(R.id.powered_by_who) as TextView
        poweredByView.text = getString(when(engine) {
            "Google" -> R.string.powered_by_Google
            "DeepL" -> R.string.powered_by_DeepL
            else -> R.string.powered_by_DeepL
        })

    }
}