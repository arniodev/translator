package com.arniodev.translator.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.arniodev.translator.R
import com.arniodev.translator.adapter.HomepageAdapter
import com.arniodev.translator.data.HomepageItem
import com.arniodev.translator.utils.LangUtils

class MainActivity : AppCompatActivity() {

    private fun configChecker() {
        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val configured = prefs.getBoolean("configured",false)

        Log.d("ArT",configured.toString())

        if(!configured) {
            val intent = Intent(this, InitialActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configChecker()

        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val engine = prefs?.getString("engine","DeepL")!!
        val fromLang = prefs.getString("fromLang","zh-CN")!!
        val toLang = prefs.getString("toLang","en")!!
        val poweredByView = findViewById<View>(R.id.powered_by_who) as TextView
        poweredByView.text = getString(LangUtils.getEnginePoweredBy(engine))

        val viewPager = findViewById<View>(R.id.more_view_pager) as ViewPager2
        viewPager.adapter = HomepageAdapter(listOf(
            HomepageItem(
                R.drawable.earth,
                "${getString(R.string.to)} ${getString(LangUtils.getLang(toLang))}",
                "${getString(R.string.from)} ${getString(LangUtils.getLang(fromLang))}"
            ){},
            HomepageItem(
                R.drawable.translation_engine_choosing_icon,
                getString(R.string.translation_engine),
                getString(R.string.switch_engine)
            ){},
            HomepageItem(
                R.drawable.coffee_cup,
                getString(R.string.buy_me_a_coffee),
                getString(R.string.buy_me_a_coffee_descr)
            ){
                val intent = Intent(this,GoToDonateActivity::class.java)
                startActivity(intent)
            },
        ))

    }
}