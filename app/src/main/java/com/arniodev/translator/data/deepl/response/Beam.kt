package com.arniodev.translator.data.deepl.response

data class Beam(
    val sentences: List<Sentence>,
    val num_symbols: Int
)
