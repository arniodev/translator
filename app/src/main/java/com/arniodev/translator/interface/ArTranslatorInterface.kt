package com.arniodev.translator.`interface`

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ArTranslatorInterface {
    @Headers("User-Agent: ArTranslator/11.45.14",
        "Accept: */*",
        "Connection: keep-alive",
        //"Accept-Encoding: gzip,br",
        "Content-Type: */*")
    @GET("/deviceList")
    fun getDeviceList(): Call<ResponseBody>

    @Headers("User-Agent: ArTranslator/11.45.14",
        "Accept: */*",
        "Connection: keep-alive",
        //"Accept-Encoding: gzip,br",
        "Content-Type: */*")
    @GET("/cgi-cdn/trace")
    fun getNetworkStatus(): Call<ResponseBody>
}