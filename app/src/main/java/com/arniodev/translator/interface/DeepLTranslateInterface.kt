package com.arniodev.translator.`interface`

import com.arniodev.translator.data.deepl.request.LMTRequest
import com.google.gson.JsonElement
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DeepLTranslateInterface {
    @Headers("User-Agent: ArTranslator/11.45.14",
        "Host: www2.deepl.com",
        "Accept: */*",
        "Connection: keep-alive",
        //"Accept-Encoding: gzip,br",
        "Content-Type: application/json")
    @POST("/jsonrpc?method=LMT_handle_jobs")
    fun translate(
        @Body request: RequestBody
    ): Call<JsonElement>
}