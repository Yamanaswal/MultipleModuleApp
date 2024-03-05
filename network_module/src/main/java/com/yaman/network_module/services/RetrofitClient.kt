package com.yaman.network_module.services

import android.content.Context
import com.yaman.network_module.models.NetworkConfiguration
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient {

    fun getClient(
        context: Context,
        baseUrl: String,
        okHttpClient: OkHttpClient = OkHttpClientGenerator().createBasic(context)
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    fun createClient(networkConfiguration: NetworkConfiguration): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(networkConfiguration.baseUrl)
        networkConfiguration.convertors.forEach { factory ->
            retrofitBuilder.addConverterFactory(factory)
        }
        retrofitBuilder.client(networkConfiguration.okHttpClient)
        return retrofitBuilder.build()
    }

}