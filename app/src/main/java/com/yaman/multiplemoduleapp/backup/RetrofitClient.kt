//package com.yaman.retrofit
//
//import retrofit2.Converter
//import retrofit2.Retrofit
//
//object RetrofitClient {
//
//    private var retrofit: Retrofit? = null
//
//    fun buildClient(
//        baseUrl: String,
//        convertor: Converter.Factory = RetrofitConvertors.getGsonConvertor(),
//        httpClientBuilder: HttpClientBuilder = HttpClientBuilder(
//            networkInterceptor = Interceptors.networkInterceptor(),
//            httpLoggingInterceptor = Interceptors.httpLoggingInterceptor()
//        )
//    ) : Retrofit? {
//        if (retrofit == null) {
//            retrofit = Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(convertor)
//                .client(httpClientBuilder.build())
//                .build()
//        }
//        return retrofit
//    }
//
//
//}
//
//
