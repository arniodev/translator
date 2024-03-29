package com.arniodev.translator.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.arniodev.translator.R

class InitFinishedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_finished)

        val finishedLayout = findViewById<View>(R.id.finished_layout)
        val prefs = getSharedPreferences("config", MODE_PRIVATE).edit()

        finishedLayout.setOnClickListener {
            prefs.putBoolean("configured", true)
            prefs.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }

        val backBtnView = findViewById<View>(R.id.back_btn)
        backBtnView.setOnClickListener {
            finish()
        }
    }
}