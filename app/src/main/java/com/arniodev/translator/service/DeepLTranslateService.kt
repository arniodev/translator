package com.arniodev.translator.service

import android.util.Log
import com.arniodev.translator.`interface`.DeepLTranslateInterface
import com.arniodev.translator.data.deepl.request.*
import com.arniodev.translator.data.deepl.response.TranslationResult
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.random.Random

class DeepLTranslateService {

    private val BASE_URL = "https://www2.deepl.com/"
    private val gson = Gson()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create<DeepLTranslateInterface>()

    fun translate(text: String, sourceLang: String, targetLang: String): String? {
        val requestData = LMTRequest(
            "2.0",
            "LMT_handle_jobs",
            JobsParams(
                listOf(
                    LMTJob(
                        "default",
                        listOf(
                            Sentence(
                                text,
                                0,
                                ""
                            )
                        ),
                        emptyList(),
                        emptyList(),
                        1,
                        "fast"
                    )
                ),
                Lang(
                    sourceLang,
                    targetLang
                ),
                -1,
                CommonJobsParams(
                    "translate",
                    1
                ),
                System.currentTimeMillis()/1000
            ),
            generateId()
        )
        Log.d("ArT",gson.toJson(requestData).toString())
        val request = service.translate(requestData)
        val response = request.execute()
        Log.d("ArT",response.toString())
        Log.d("ArT",request.toString())
        if (response.isSuccessful) {
            Log.d("ArT",response.body().toString())
            val responseJson = gson.fromJson(response.body(),TranslationResult::class.java)
            return responseJson.translations[0].beams[0].sentences[0].text
        }

        return null

    }

    private fun generateId() = Random.nextInt(10000000,100000000)

}