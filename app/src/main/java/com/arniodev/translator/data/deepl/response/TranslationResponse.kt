package com.arniodev.translator.data.deepl.response

data class TranslationResponse(
    val jsonrpc: String,
    val id: Int,
    val result: TranslationResult
)
