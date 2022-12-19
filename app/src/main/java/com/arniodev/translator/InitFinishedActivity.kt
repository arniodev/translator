package com.arniodev.translator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit

class InitFinishedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_finished)

        val finishedLayout = findViewById<View>(R.id.finished_layout)
        val prefs = getSharedPreferences("config", MODE_PRIVATE)

        finishedLayout.setOnClickListener {
            prefs.edit {
                putBoolean("configured",true)
            }
            finish()
            TODO("Start MainActivity")
        }
    }
}