package com.arniodev.translator.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.arniodev.translator.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        val prefs = activity?.getSharedPreferences("config", AppCompatActivity.MODE_PRIVATE)
        val engine = prefs?.getString("engine","DeepL")
        val poweredByView = view?.findViewById<View>(R.id.powered_by_who) as TextView
        val text = getString(when(engine) {
            "Google" -> R.string.powered_by_Google
            "DeepL" -> R.string.powered_by_DeepL
            else -> R.string.powered_by_DeepL
        })

        Log.d("ArT",text)

        poweredByView.text = text
    }

}