package com.arniodev.translator.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.arniodev.translator.R
import com.arniodev.translator.service.GoogleTranslateService
import com.arniodev.translator.utils.LangUtils
//import com.arniodev.translator.data.GoogleTranslateResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlin.concurrent.thread

class TextTranslateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_translate)
        // binding = ActivityTextTranslateBinding.inflate(layoutInflater)

        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val googleTranslate = GoogleTranslateService()

        val engine = prefs?.getString("engine","DeepL")!!
        val fromLang = LangUtils.getLangId(engine,prefs.getString("fromLang","zh-CN")!!)
        val toLang = LangUtils.getLangId(engine,prefs.getString("toLang","en")!!)
        val fabView = findViewById<View>(R.id.fab_done)

        fabView.setOnClickListener {
            val editTextView = findViewById<View>(R.id.text_editor) as EditText
            val text2translate = editTextView.text.toString()
            Log.d("ArT",text2translate)
            if(text2translate == ""){
                Toast.makeText(this,getString(R.string.cannot_be_empty),Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            thread {
                try {
                    googleTranslate.translate(text2translate,toLang,fromLang)?.let { it1 -> Log.d("ArT", it1) }
                } catch (e: Throwable) {
                    Toast.makeText(this,getString(R.string.network_error),Toast.LENGTH_SHORT).show()
                    return@thread
                }
            }
//            val translateResult = when(engine) {
//                    "Google" -> translateByGoogle(text2translate.toString())
//                    else -> {
//                        //translateByDeepL(text2translate)
//                    }
//            }
        }
    }

//    private fun translateByDeepL(text: String): String {
//
//    }

    private fun translateByGoogle(text: String): String {
        runBlocking {

        }
    return "null"
    }
}