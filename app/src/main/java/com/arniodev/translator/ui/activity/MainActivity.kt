package com.arniodev.translator.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.arniodev.translator.R
import com.arniodev.translator.adapter.HomepageAdapter
import com.arniodev.translator.data.HomepageItem
import com.arniodev.translator.utils.LangUtils
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView
import android.text.style.AbsoluteSizeSpan
import android.text.Spanned
import com.arniodev.translator.service.ArTranslatorService
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private lateinit var viewPager: ViewPager2
    private lateinit var fromLang: String
    private lateinit var toLang: String

    var changeLang = false

    private lateinit var homepageList: MutableList<HomepageItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //configChecker()

        setContentView(R.layout.activity_main)

        val service = ArTranslatorService()

        prefs = getSharedPreferences("config", MODE_PRIVATE)
        val engine = prefs.getString("engine","DeepL")!!
        fromLang = prefs.getString("fromLang","zh-CN")!!
        toLang = prefs.getString("toLang","en")!!

        Log.d("ArT","Lang $fromLang $toLang")

        val textTranslateView = findViewById<View>(R.id.quick_translate)
        val poweredByView = findViewById<View>(R.id.powered_by_who) as TextView
        poweredByView.text = getString(LangUtils.getEnginePoweredBy(engine))

        textTranslateView.setOnClickListener {
            val intent = Intent(this, TextTranslateActivity::class.java)
            startActivity(intent)
        }

        homepageList = mutableListOf(
            HomepageItem(
                R.drawable.earth,
                "${getString(R.string.to)} ${getString(LangUtils.getLang(toLang))}",
                "${getString(R.string.from)} ${getString(LangUtils.getLang(fromLang))}"
            ){
                changeLang = true
                val intent = Intent(this,LangChoosingActivity::class.java)
                intent.putExtra("title", getString(R.string.source_lang))
                intent.putExtra("prefs","fromLang")
                startActivity(intent)
            },
            HomepageItem(
                R.drawable.translation_engine_choosing_icon,
                getString(R.string.translation_engine),
                getString(R.string.switch_engine)
            ){
                val intent = Intent(this,EngineChoosingActivity::class.java)
                startActivity(intent)
            },
            HomepageItem(
                R.drawable.coffee_cup,
                getString(R.string.buy_me_a_coffee),
                getString(R.string.buy_me_a_coffee_descr)
            ){
                val intent = Intent(this,GoToDonateActivity::class.java)
                startActivity(intent)
            },
            HomepageItem(
                R.drawable.about,
                getString(R.string.about_us),
                getString(R.string.ArT_about)
            ){
                val intent = Intent(this,AboutActivity::class.java)
                startActivity(intent)
                // finish()
            }
        )

        viewPager = findViewById<View>(R.id.more_view_pager) as ViewPager2
        viewPager.adapter = HomepageAdapter(homepageList)

        val content = SpannableStringBuilder(getString(R.string.try2swipe))
        content.setSpan(AbsoluteSizeSpan(11, true), 0, content.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        MaterialShowcaseView.Builder(this)
            .setTarget(viewPager)
            .setContentText(content)
            .setDelay(500)
            .singleUse("10086001")
            .setDismissOnTouch(true)
            .setDismissOnTargetTouch(true)
            .show()

        thread {
            if(service.checkUpdate()) {
                Log.d("ArT","UPDATE")
            }
        }

    }

    override fun onResume() {
        super.onResume()

        val newPrefs = getSharedPreferences("config", MODE_PRIVATE)

        if(changeLang) {
            val adapter = viewPager.adapter as HomepageAdapter
            val newFromLang = newPrefs.getString("fromLang","zh-CN")!!
            val newToLang = newPrefs.getString("toLang","en")!!

            setNewLang(newFromLang,newToLang)

            Log.d("ArT","$newFromLang $newToLang $homepageList")

            viewPager.adapter = HomepageAdapter(homepageList)

            adapter.notifyItemChanged(0)

            changeLang = false
        }

        val poweredByView = findViewById<View>(R.id.powered_by_who) as TextView
        val newEngine = newPrefs.getString("engine","DeepL")!!
        poweredByView.text = getString(LangUtils.getEnginePoweredBy(newEngine))
    }

    private fun setNewLang(fromLang: String, toLang: String) {
        homepageList[0].firstLine = "${getString(R.string.to)} ${getString(LangUtils.getLang(toLang))}"
        homepageList[0].secondLine = "${getString(R.string.from)} ${getString(LangUtils.getLang(fromLang))}"
    }

}