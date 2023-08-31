package com.yaman.network_module

import android.content.Context
import com.yaman.network_module.services.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/* Main File - Network Module */
object NetworkCore {

    fun getNetworkService(context: Context,baseUrl: String): Retrofit {
        return RetrofitClient().getClient(context,baseUrl)
    }

    fun getNetworkService(context: Context,baseUrl: String,okHttpClient: OkHttpClient): Retrofit {
        return RetrofitClient().getClient(context,baseUrl,okHttpClient)
    }

}
