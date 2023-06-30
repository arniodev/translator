package com.arniodev.translator.data.deepl.response

data class Translation(
    val beams: List<Beam>,
    val quality: String
)
