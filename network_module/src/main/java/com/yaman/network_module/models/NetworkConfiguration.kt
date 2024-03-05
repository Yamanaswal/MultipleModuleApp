package com.yaman.network_module.models

import okhttp3.OkHttpClient
import retrofit2.Converter

data class NetworkConfiguration(
    val baseUrl: String = "no base url found..",
    val okHttpClient: OkHttpClient,
    val convertors: List<Converter.Factory> = arrayListOf(),
)
