package com.arniodev.translator.service

import android.util.Log
import com.arniodev.translator.`interface`.ArTranslatorInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

class ArTranslatorService {

    private val BASE_URL = "https://cdn.pai233.top/"
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
        .client(client)
        .build()
    private val service = retrofit.create<ArTranslatorInterface>()

    fun checkDevices(deviceID: String): Boolean {
        val request = service.getDeviceList()
        val response = request.execute()
        if(response.isSuccessful) {
            val devicesList = response.body()?.string()?.split("\n")
            if(devicesList is List<String>) {
                Log.d("ArT","List! $deviceID ${devicesList.size} $devicesList")
                if(devicesList.indexOf(deviceID)==-1) {
                    return false
                }
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }

    fun checkNetwork(): Map<String,String>? {
        val request = service.getNetworkStatus()
        val response = request.execute()
        return if(response.isSuccessful) {
            val originalData = response.body()?.string()?.split("\n")?.map {
                it.split("=")
            }?.associate {
                it[0] to it[1]
            }
            originalData
        } else {
            null
        }
    }

}