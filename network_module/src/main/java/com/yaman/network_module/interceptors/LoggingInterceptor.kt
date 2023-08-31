package com.yaman.network_module.interceptors

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level

class LoggingInterceptor {

    private val logging = HttpLoggingInterceptor()

    init {
        logging.level = Level.BASIC
    }

    fun create(level: Level = Level.BASIC): HttpLoggingInterceptor {
        logging.level = level
        return logging
    }

}