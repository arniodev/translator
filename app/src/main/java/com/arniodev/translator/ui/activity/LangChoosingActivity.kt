package com.arniodev.translator.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arniodev.translator.R
import com.arniodev.translator.adapter.LanguageAdapter
import com.arniodev.translator.ui.RecyclerDecoration
import com.arniodev.translator.utils.LangUtils

class LangChoosingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lang_choosing)

        if (intent.getStringExtra("title") == null) {
            finish()
        }

        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val engine = prefs?.getString("engine","DeepL")!!

        val title = intent.getStringExtra("title")
        findViewById<TextView>(R.id.lang_choosing_title).text = title

        val langList = LangUtils.getSupportList(engine)

        val recyclerView = findViewById<View>(R.id.lang_view) as RecyclerView

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@LangChoosingActivity)

            adapter = LanguageAdapter(this@LangChoosingActivity,langList) {

                if(intent.getStringExtra("chose") == it) {
                    Toast.makeText(this@LangChoosingActivity,getString(R.string.cannot_be_same),Toast.LENGTH_LONG).show()
                    return@LanguageAdapter
                }

                Log.d("ArT",it)

                val sp = prefs.edit()

                sp.putString(intent.getStringExtra("prefs")!!,it)
                sp.apply()

                val newIntent = Intent(this@LangChoosingActivity,LangChoosingActivity::class.java)

                if(intent.getStringExtra("title") == getString(R.string.target_lang)) {
                    finish()
                    return@LanguageAdapter
                }

                newIntent.putExtra("title", getString(R.string.target_lang))
                newIntent.putExtra("prefs","toLang")
                newIntent.putExtra("chose",it)
                startActivity(newIntent)

                finish()
            }

            addItemDecoration(RecyclerDecoration())
        }

    }
}