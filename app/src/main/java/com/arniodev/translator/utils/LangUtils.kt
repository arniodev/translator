package com.arniodev.translator.utils

import com.arniodev.translator.R

object LangUtils {

    fun getLang(lang: String) = when (lang) {
        "zh-CN" -> R.string.zh_CN
        "en" -> R.string.en
        else -> R.string.en
    }

    fun getEnginePoweredBy(engine: String) = when(engine) {
        "Google" -> R.string.powered_by_Google
        "DeepL" -> R.string.powered_by_DeepL
        else -> R.string.powered_by_DeepL
    }

    fun getLangId(engine: String, lang: String) = when(engine) {
        "Google" -> lang
        "DeepL" -> {
            when(lang) {
                "zh-CN" -> "ZH"
                "en" -> "EN"
                else -> "EN"
            }
        }
        else -> { // Same as DeepL
            when(lang) {
                "zh-CN" -> "ZH"
                "en" -> "EN"
                else -> "EN"
            }
        }
    }

}