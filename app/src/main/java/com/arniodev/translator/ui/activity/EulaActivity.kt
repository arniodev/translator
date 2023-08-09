package com.arniodev.translator.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.arniodev.translator.R

class EulaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eula)
        val agreeView = findViewById<View>(R.id.agreeView)

        val backBtnView = findViewById<View>(R.id.back_btn)
        backBtnView.setOnClickListener {
            finish()
        }

        agreeView.setOnClickListener {
            if(intent.getBooleanExtra("init",false)){
                val intent = Intent(this,EngineChoosingActivity::class.java)
                intent.putExtra("init",true)
                startActivity(intent)
            }
            finish()
        }
    }
}