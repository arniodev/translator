package com.arniodev.translator.data.deepl.response

data class TranslationResult(
    val translations: List<Translation>,
    val target_lang: String,
    val source_lang: String
)