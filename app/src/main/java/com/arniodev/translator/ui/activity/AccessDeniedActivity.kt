package com.arniodev.translator.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import com.arniodev.translator.R

class AccessDeniedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_denied)

        val idView = findViewById<TextView>(R.id.id_view)
        val id = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        idView.text = "AID: $id"
    }
}