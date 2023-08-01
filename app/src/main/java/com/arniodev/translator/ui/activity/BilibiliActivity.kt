package com.arniodev.translator.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arniodev.translator.R

class BilibiliActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bilibili)

        val backBtnView = findViewById<View>(R.id.back_btn)
        val switchView = findViewById<View>(R.id.switch_one)
        backBtnView.setOnClickListener {
            finish()
        }

        switchView.setOnClickListener {
            startActivity(Intent(this,QQGroupActivity::class.java))
            finish()
        }

    }
}