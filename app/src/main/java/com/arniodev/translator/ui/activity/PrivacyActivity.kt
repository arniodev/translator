package com.arniodev.translator.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arniodev.translator.R
import com.arniodev.translator.adapter.PrivacyAdapter
import com.arniodev.translator.data.PrivacyItem
import com.arniodev.translator.ui.RecyclerDecoration

class PrivacyActivity : AppCompatActivity() {

    private val privacyList = ArrayList<PrivacyItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

        loadPrivacyList()

        val privacyListRecyclerView = findViewById<View>(R.id.privacy_list) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        val adapter = PrivacyAdapter(privacyList)
        privacyListRecyclerView.layoutManager = layoutManager
        privacyListRecyclerView.adapter = adapter
        privacyListRecyclerView.addItemDecoration(RecyclerDecoration())

        val backBtnView = findViewById<View>(R.id.back_btn)
        val agreePrivacyView = findViewById<View>(R.id.agreePrivacy)
        backBtnView.setOnClickListener {
            finish()
        }
        agreePrivacyView.setOnClickListener {
            val intent = Intent(applicationContext, EngineChoosingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadPrivacyList() {
        privacyList.add(
            PrivacyItem(
                R.drawable.internet_connection,
                getString(R.string.internet_connection),
                getString(R.string.internet_connection_descr)
            )
        )
    }
}