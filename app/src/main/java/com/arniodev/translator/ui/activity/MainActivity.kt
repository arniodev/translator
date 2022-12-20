package com.arniodev.translator.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

    }
}