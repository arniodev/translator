package com.arniodev.translator.data.deepl.request

import com.arniodev.translator.data.deepl.request.JobsParams

data class LMTRequest(
    val jsonrpc: String,
    val method: String,
    val params: JobsParams,
    val id: Int
)
