package com.arniodev.translator.`interface`

import com.arniodev.translator.data.deepl.request.LMTRequest
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DeepLTranslateInterface {
    @POST("/jsonrpc?method=LMT_handle_jobs")
    fun translate(
        @Body request: LMTRequest
    ): Call<JsonElement>
}