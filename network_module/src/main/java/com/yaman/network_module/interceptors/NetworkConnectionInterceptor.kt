package com.yaman.network_module.interceptors

import android.content.Context
import android.net.ConnectivityManager
import com.yaman.network_module.exceptions.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            // Throwing our custom exception 'NoConnectivityException'
            throw NoConnectivityException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}