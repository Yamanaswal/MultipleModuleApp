//package com.yaman.retrofit
//
//import okhttp3.Headers
//import okhttp3.Interceptor
//import okhttp3.Request
//import okhttp3.logging.HttpLoggingInterceptor
//
//object Interceptors {
//
//    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        return httpLoggingInterceptor
//    }
//
//    fun networkInterceptor(): Interceptor {
//        return Interceptor { chain: Interceptor.Chain ->
//
//            val request: Request.Builder = chain.request().newBuilder()
//            request.method(chain.request().method, chain.request().body)
//            request.build()
//
//            chain.proceed(request.build())
//        }
//    }
//
//    fun networkInterceptorWithHeaders(headers: Headers): Interceptor {
//        return Interceptor { chain: Interceptor.Chain ->
//
//            val request: Request.Builder = chain.request().newBuilder()
//            request.method(chain.request().method, chain.request().body)
//            request.headers(headers)
//            request.build()
//
//            chain.proceed(request.build())
//        }
//    }
//}