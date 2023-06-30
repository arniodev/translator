package com.arniodev.translator.`interface`

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface GoogleTranslateInterface {
    @GET("/translate_a/single")
    fun translate(
        @Query("client") client: String,
        @Query("sl") sourceLanguage: String,
        @Query("tl") targetLanguage: String,
        @Query("dt") dt: String,
        @Query("q") query: String,
        @Query("tk") token: String
    ): Call<JsonElement>
}