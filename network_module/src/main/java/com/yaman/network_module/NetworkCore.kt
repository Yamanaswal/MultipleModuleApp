package com.yaman.network_module

import android.content.Context
import com.yaman.network_module.models.NetworkConfiguration
import com.yaman.network_module.services.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/* Main File - Network Module */
object NetworkCore {

    fun <T> createNetworkClient(context: Context, networkConfiguration: NetworkConfiguration, service: Class<T>): T {
        val client = RetrofitClient().createClient(networkConfiguration)
        return client.create(service)
    }

}
