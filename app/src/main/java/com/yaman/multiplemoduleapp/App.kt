package com.yaman.multiplemoduleapp

import android.app.Application
import android.util.Log
import com.yaman.common_utils.exception_handlers.ExceptionListener
import com.yaman.common_utils.exception_handlers.GlobalExceptionHandler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.setupExceptionHandler(object : ExceptionListener {
            override fun uncaughtException(thread: Thread, throwable: Throwable) {
                Log.e("Application Level: ", "uncaughtException: " + throwable.localizedMessage)
                throwable.printStackTrace()
            }
        })
    }

}


