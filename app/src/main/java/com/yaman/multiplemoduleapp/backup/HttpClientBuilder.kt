//package com.yaman.retrofit
//
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import java.util.concurrent.TimeUnit
//
//class HttpClientBuilder(
//    private val timeout: Long = 100,
//    private val unit: TimeUnit = TimeUnit.SECONDS,
//    private val networkInterceptor: Interceptor? = null,
//    private val httpLoggingInterceptor: HttpLoggingInterceptor? = null,
//    private val isLogEnable: Boolean = true,
//    private val interceptors: List<Interceptor>? = null,
//) {
//
//    fun build(): OkHttpClient {
//        val okHttpClientBuilder = OkHttpClient.Builder()
//
//        okHttpClientBuilder
//            .connectTimeout(timeout, unit)
//            .readTimeout(timeout, unit)
//
//        networkInterceptor?.let {
//            okHttpClientBuilder.addNetworkInterceptor(networkInterceptor)
//        }
//        httpLoggingInterceptor?.let {
//            /* Enable/Disable Debugging Http Logs */
//            if (isLogEnable) {
//                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor!!)
//            }
//        }
//        interceptors?.forEach { interceptors ->
//            okHttpClientBuilder.addInterceptor(interceptors)
//        }
//
//
//        return okHttpClientBuilder.build()
//    }
//}
//
