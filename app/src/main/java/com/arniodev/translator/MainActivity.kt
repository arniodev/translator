package com.arniodev.translator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private fun configChecker(): Unit {
        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val configured = prefs.getBoolean("configured",false)

        if(!configured) {
            val intent = Intent(this,InitialActivity::class.java)
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