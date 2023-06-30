package com.arniodev.translator.data.deepl.request

data class JobsParams(
    val jobs: List<LMTJob>,
    val lang: Lang,
    val priority: Int,
    val commonJobsParams: CommonJobsParams,
    val timestamp: Long
)
