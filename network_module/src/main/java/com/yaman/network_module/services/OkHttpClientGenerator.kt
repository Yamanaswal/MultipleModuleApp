package com.yaman.network_module.services;

import android.content.Context
import com.yaman.network_module.interceptors.LoggingInterceptor
import com.yaman.network_module.interceptors.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor.Level

class OkHttpClientGenerator {

    fun createCore(level: Level = Level.BASIC): OkHttpClient {
        return OkHttpClient.Builder()
            //Logging Level
            .addInterceptor(LoggingInterceptor().create(level))
            .build()
    }

    fun createBasic(context: Context, level: Level = Level.BASIC): OkHttpClient {
        return OkHttpClient.Builder()
            //Network Connection - internet is on/off
            .addInterceptor(NetworkConnectionInterceptor(context))
            //Logging Level
            .addInterceptor(LoggingInterceptor().create(level))
            .build()
    }

}
