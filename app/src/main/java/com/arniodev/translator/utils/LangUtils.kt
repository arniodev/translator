package com.arniodev.translator.utils

import com.arniodev.translator.R

object LangUtils {

    fun getLang(lang: String) = when (lang) {
        "zh-CN" -> R.string.zh_CN
        "en" -> R.string.en
        else -> R.string.en
    }

    fun getEngine(engine: String) = when(engine) {
        "Google" -> R.string.powered_by_Google
        "DeepL" -> R.string.powered_by_DeepL
        else -> R.string.powered_by_DeepL
    }

}