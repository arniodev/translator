package com.arniodev.translator.service

import android.util.Log
import com.arniodev.translator.`interface`.DeepLTranslateInterface
import com.arniodev.translator.data.deepl.request.*
import com.arniodev.translator.data.deepl.response.TranslationResponse
import com.arniodev.translator.data.deepl.response.TranslationResult
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.nio.charset.Charset
import java.util.Arrays
import kotlin.random.Random

class DeepLTranslateService {

    private val BASE_URL = "https://www2.deepl.com/"
    private val gson = Gson()
    private val interceptor = HttpLoggingInterceptor { message ->
        Log.d(
            "ArT_requestBody",
            ">>>>>>>>>$message"
        )
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(client)
        .build()
    private val service = retrofit.create<DeepLTranslateInterface>()

    fun translate(text: String, sourceLang: String, targetLang: String): String {
        val id = generateId()
        val requestDataObj = LMTRequest(
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
            id
        )

        var requestData = gson.toJson(requestDataObj).toString()

        //DeepL的神奇风控方式？！
        requestData = if((id + 5) % 29 == 0 || (id + 3) % 13 == 0) {
            requestData.replace("\"method\":\"", "\"method\" : \"")
        } else {
            requestData.replace("\"method\":\"", "\"method\": \"")
        }

        val request = service.translate(requestData.toRequestBody("text/plain".toMediaTypeOrNull()))
        val response = request.execute()
        //Log.d("ArT_DATA",requestData)
        if (response.isSuccessful) {
            //response.body()?.bytes().let { it?.let { it1 -> Log.d("ArT", it1.toString(Charsets.UTF_8)) } }
            val responseJson = gson.fromJson(response.body(),TranslationResponse::class.java)
            return responseJson.result.translations[0].beams[0].sentences[0].text
        }

        return ""

    }

    private fun generateId() = Random.nextInt(16168527,91918759) // Just4Fun Lmaooooooooo

}