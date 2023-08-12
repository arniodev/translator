package com.arniodev.translator.service

import android.util.Log
import com.arniodev.translator.`interface`.GoogleTranslateInterface
import com.arniodev.translator.utils.getGoogleTK
import com.google.gson.JsonArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class GoogleTranslateService {

    private val BASE_URL = "https://translate.google.com/"
    private val client = "webapp"
    private val dt = "t"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create<GoogleTranslateInterface>()

    fun translate(query: String, targetLanguage: String, sourceLanguage: String): String {
        val request = service.translate(client, sourceLanguage, targetLanguage, dt, query, query.getGoogleTK())
        val response = request.execute()
        if (response.isSuccessful) {
            val translationResponse = response.body()?.asJsonArray
            if (translationResponse != null && translationResponse.size() > 0) {
                val firstElement = translationResponse[0]
                if (firstElement is JsonArray) {
                    val translationArray = firstElement[0]
                    if (translationArray is JsonArray) {
                        Log.d("ArT_R",translationArray[0].asString)
                        return translationArray[0].asString
                    }
                }
            }
        }
        return ""
    }

}