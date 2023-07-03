package com.arniodev.translator.data.deepl.response

import org.intellij.lang.annotations.Language

data class TranslationResult(
    val translations: List<Translation>,
    val target_lang: String,
    val source_lang: String,
    val source_lang_is_confident: String,
    val detectedLanguage: Map<String, Double>
)