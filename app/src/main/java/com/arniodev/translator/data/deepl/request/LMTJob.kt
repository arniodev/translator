package com.arniodev.translator.data.deepl.request

data class LMTJob(
    val kind: String,
    val sentences: List<Sentence>,
    val raw_en_context_before: List<Any>,
    val raw_en_context_after: List<Any>,
    val preferred_num_beams: Int,
    val quality: String
)
