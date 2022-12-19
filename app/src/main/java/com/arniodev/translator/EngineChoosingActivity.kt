package com.arniodev.translator

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EngineChoosingActivity : AppCompatActivity() {

    private val engineList = ArrayList<EngineItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_engine_choosing)

        loadEngineList()

        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val arrowView = findViewById<View>(R.id.engine_next_step_arrow) as ImageView
        val nextBtnView = findViewById<View>(R.id.engine_next_step)
        val engineRecyclerView = findViewById<View>(R.id.engine_list) as RecyclerView
        val adapter = EngineAdapter(this,engineList)
        val layoutManager = LinearLayoutManager(this)

        engineRecyclerView.layoutManager = layoutManager
        engineRecyclerView.adapter = adapter
        engineRecyclerView.addItemDecoration(RecyclerDecoration())

        nextBtnView.setOnClickListener {
            if( arrowView.alpha == 1F) {
                prefs.edit {
                    putString("engine", adapter.getEngine())
                }
            }
        }
    }

    private fun loadEngineList() {
        engineList.add(
            EngineItem(
                R.drawable.google_translate_icon,
                getString(R.string.google_translate),
                "Google"
            )
        )
        engineList.add(
            EngineItem(
                R.drawable.deepl_icon,
                getString(R.string.deepl_translate),
                "DeepL"
            )
        )
    }
}