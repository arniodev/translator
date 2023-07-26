package com.arniodev.translator.`interface`

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ArTranslatorInterface {
    @Headers("User-Agent: ArTranslator/11.45.14",
        "Accept: */*",
        "Connection: keep-alive",
        //"Accept-Encoding: gzip,br",
        "Content-Type: */*")
    @GET("/insiderDevicesList")
    fun getDeviceList(): Call<ResponseBody>

    @Headers("User-Agent: ArTranslator/11.45.14",
        "Accept: */*",
        "Connection: keep-alive",
        //"Accept-Encoding: gzip,br",
        "Content-Type: */*")
    @GET("/cgi-cdn/trace")
    fun getNetworkStatus(): Call<ResponseBody>

    @Headers("User-Agent: ArTranslator/11.45.14",
        "Accept: */*",
        "Connection: keep-alive",
        //"Accept-Encoding: gzip,br",
        "Content-Type: */*")
    @GET("/changelog-{lang}")
    fun getChangelog(@Path("lang") langStr: String): Call<ResponseBody>

    @Headers("User-Agent: ArTranslator/11.45.14",
        "Accept: */*",
        "Connection: keep-alive",
        //"Accept-Encoding: gzip,br",
        "Content-Type: */*")
    @GET("/versionCode")
    fun getVersionCode(): Call<Int>

    @Headers("User-Agent: ArTranslator/11.45.14",
        "Accept: */*",
        "Connection: keep-alive",
        //"Accept-Encoding: gzip,br",
        "Content-Type: */*")
    @GET("/versionName")
    fun getVersionName(): Call<ResponseBody>
}