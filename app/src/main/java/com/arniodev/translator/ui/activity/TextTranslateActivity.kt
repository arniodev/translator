package com.arniodev.translator.ui.activity

//import com.arniodev.translator.data.GoogleTranslateResult
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arniodev.translator.R
import com.arniodev.translator.service.DeepLTranslateService
import com.arniodev.translator.service.GoogleTranslateService
import com.arniodev.translator.utils.LangUtils
import com.arniodev.translator.utils.dp2px
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.concurrent.thread

class TextTranslateActivity : AppCompatActivity() {

    private val NETWORK_ERROR = 0x1
    private val SHOW_DRAWER = 0x2
    private lateinit var _CONTEXT: Context
    private lateinit var drawer: BottomSheetBehavior<View>
    var translateResult = ""

    private val handler = object : Handler(Looper.getMainLooper()){

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what) {
                NETWORK_ERROR -> {
                    Toast.makeText(_CONTEXT,getString(R.string.network_error),Toast.LENGTH_SHORT).show()
                }
                SHOW_DRAWER -> {
                    Log.d("ArT",translateResult)

                    if (translateResult == "") {
                        Toast.makeText(_CONTEXT,getString(R.string.result_empty),Toast.LENGTH_SHORT).show()
                        return
                    }

                    findViewById<FloatingActionButton>(R.id.fab_done).apply {
                        visibility = View.GONE
                        setImageResource(R.drawable.go)
                    }
                    drawer.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_translate)
        // binding = ActivityTextTranslateBinding.inflate(layoutInflater)

        if(!::_CONTEXT.isInitialized) {
            _CONTEXT = this
        }

        val prefs = getSharedPreferences("config", MODE_PRIVATE)
        val googleTranslate = GoogleTranslateService()
        val deeplTranslate = DeepLTranslateService()

        val engine = prefs?.getString("engine","DeepL")!!
        val fromLang = LangUtils.getLangId(engine,prefs.getString("fromLang","zh-CN")!!)
        val toLang = LangUtils.getLangId(engine,prefs.getString("toLang","en")!!)
        val fabView = findViewById<FloatingActionButton>(R.id.fab_done)
        drawer = BottomSheetBehavior.from(findViewById<LinearLayout>(R.id.result_drawer))

        drawer.peekHeight = 30.dp2px(this)
        drawer.isDraggable = false
        drawer.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        findViewById<LinearLayout>(R.id.drawer_collapsed).apply {
                            visibility = View.GONE
                        }
                        findViewById<LinearLayout>(R.id.drawer_main).apply {
                            visibility = View.VISIBLE
                        }
                        findViewById<TextView>(R.id.result_view).apply {
                            text = translateResult
                        }
                        findViewById<TextView>(R.id.result_from_view).apply {
                            text = getString(LangUtils.getEnginePoweredBy(engine))
                        }
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        fabView.visibility = View.VISIBLE
                        findViewById<LinearLayout>(R.id.drawer_collapsed).apply {
                            visibility = View.VISIBLE
                        }
                        findViewById<LinearLayout>(R.id.drawer_main).apply {
                            visibility = View.GONE
                        }
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Nothing to do
                // The drawer is not draggable
            }
        })

        findViewById<LinearLayout>(R.id.fold_btn).setOnClickListener {
            drawer.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        fabView.setOnClickListener {
            //fabView.setImageResource(androidx.swiperefreshlayout.widget.SwipeRefreshLayout.DEFAULT)
            val editTextView = findViewById<View>(R.id.text_editor) as EditText
            val text2translate = editTextView.text.toString()
            Log.d("ArT",text2translate)
            if(text2translate == ""){
                fabView.setImageResource(R.drawable.go)
                Toast.makeText(this,getString(R.string.cannot_be_empty),Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            when(engine) {
                "Google" -> {
                    thread {
                        try {
                            translateResult = googleTranslate.translate(text2translate,toLang,fromLang)
                            val msg = Message()
                            msg.what = SHOW_DRAWER
                            handler.sendMessage(msg)
                        } catch (e: Throwable) {
                            val msg = Message()
                            msg.what = NETWORK_ERROR
                            handler.sendMessage(msg)
                            return@thread
                        }
                    }
                }
                "DeepL" -> {
                    thread {
                        try {
                            //Log.d("ArT","${LangUtils.getLangId("DeepL",fromLang)},${LangUtils.getLangId("DeepL",toLang)},$fromLang,$toLang")
                            translateResult = deeplTranslate.translate(text2translate,fromLang,toLang))
                            val msg = Message()
                            msg.what = SHOW_DRAWER
                            handler.sendMessage(msg)
                        } catch (e: Throwable) {
                            val msg = Message()
                            msg.what = NETWORK_ERROR
                            handler.sendMessage(msg)
                            return@thread
                        }
                    }
                }
                else -> {
                    fabView.setImageResource(R.drawable.go)
                    Toast.makeText(this,R.string.engine_invalid,Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}