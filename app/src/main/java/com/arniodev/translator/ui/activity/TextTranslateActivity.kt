package com.arniodev.translator.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arniodev.translator.R
import com.arniodev.translator.databinding.ActivityTextTranslateBinding

class TextTranslateActivity : AppCompatActivity() {

    lateinit var binding: ActivityTextTranslateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_translate)
        binding = ActivityTextTranslateBinding.inflate(layoutInflater)

    }
}