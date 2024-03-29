package com.arniodev.translator.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arniodev.translator.R

class GoToDonateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_donate)

        val backBtnView = findViewById<View>(R.id.back_btn)
        val jumpView = findViewById<View>(R.id.jump_layout)
        backBtnView.setOnClickListener {
            finish()
        }

        jumpView.setOnClickListener {
            val intent = Intent(this,WechatPayActivity::class.java)
            startActivity(intent)
        }

    }
}