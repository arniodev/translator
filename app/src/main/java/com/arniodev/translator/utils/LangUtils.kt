package com.arniodev.translator.utils

import com.arniodev.translator.R
import com.arniodev.translator.data.LanguageItem

object LangUtils {

    fun getLang(lang: String) = when (lang) {
        "zh-CN" -> R.string.zh_CN
        "en" -> R.string.en
        "ja" -> R.string.ja
        "ko" -> R.string.ko
        "ru" -> R.string.ru
        "fr" -> R.string.fr
        "vi" -> R.string.vi
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
                "ja" -> "JA"
                "ko" -> "KO"
                "ru" -> "RU"
                "fr" -> "FR"
                else -> "EN"
            }
        }
        else -> { // Same as DeepL
            when(lang) {
                "zh-CN" -> "ZH"
                "en" -> "EN"
                "ja" -> "JA"
                "ko" -> "KO"
                "ru" -> "RU"
                "fr" -> "FR"
                else -> "EN"
            }
        }
    }

    fun getSupportList(engine: String) = when(engine) { // 这里的langId不是调用API时候的语言 是ArTranslator内部的识别码 和Google翻译一样
        "Google" -> listOf(
            LanguageItem(
                R.string.en,
                "en"
            ),
            LanguageItem(
                R.string.zh_CN,
                "zh-CN"
            ),
            LanguageItem(
                R.string.ja,
                "ja"
            ),
            LanguageItem(
                R.string.ko,
                "ko"
            ),
            LanguageItem(
                R.string.ru,
                "ru"
            ),
            LanguageItem(
                R.string.fr,
                "fr"
            ),
            LanguageItem(
                R.string.vi,
                "vi"
            )
        )
        "DeepL" -> listOf(
            LanguageItem(
                R.string.en,
                "en"
            ),
            LanguageItem(
                R.string.zh_CN,
                "zh-CN"
            ),
            LanguageItem(
                R.string.ja,
                "ja"
            ),
            LanguageItem(
                R.string.ko,
                "ko"
            ),
            LanguageItem(
                R.string.ru,
                "ru"
            ),
            LanguageItem(
                R.string.fr,
                "fr"
            )
        )
        else -> listOf(
            LanguageItem(
                R.string.en,
                "en"
            ),
            LanguageItem(
                R.string.zh_CN,
                "zh-CN"
            ),
            LanguageItem(
                R.string.ja,
                "ja"
            ),
            LanguageItem(
                R.string.ko,
                "ko"
            ),
            LanguageItem(
                R.string.ru,
                "ru"
            ),
            LanguageItem(
                R.string.fr,
                "fr"
            )
        )
    }

}