package com.yaman.multiplemoduleapp

import android.app.Application
import com.yaman.common_utils.helpers.exception_handlers.ExceptionListener
import com.yaman.common_utils.helpers.exception_handlers.GlobalExceptionHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.setupExceptionHandler(object : ExceptionListener{
            override fun uncaughtException(thread: Thread, throwable: Throwable) {

            }
        })
    }


}


