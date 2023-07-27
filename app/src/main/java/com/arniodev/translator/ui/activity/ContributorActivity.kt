package com.arniodev.translator.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arniodev.translator.R

class ContributorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor)
        val backBtnView = findViewById<View>(R.id.back_btn)
        backBtnView.setOnClickListener {
            finish()
        }
    }
}