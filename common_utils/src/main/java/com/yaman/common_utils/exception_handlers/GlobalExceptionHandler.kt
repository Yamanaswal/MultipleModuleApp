package com.yaman.common_utils.exception_handlers

import android.os.Handler
import android.os.Looper
import android.util.Log

object GlobalExceptionHandler : ExceptionListener {

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        // TODO Make sure you are logging this issue some where like Crashlytics.
        // Also indicate that something went wrong to the user like maybe a dialog or an activity.
        Log.e("ExampleApp", throwable.message!!)
    }

    fun setupExceptionHandler(exceptionListener: ExceptionListener) {
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                    uncaughtException(Looper.getMainLooper().thread, e)
                    exceptionListener.uncaughtException(Looper.getMainLooper().thread, e)
                }
            }
        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            uncaughtException(t, e)
            exceptionListener.uncaughtException(t, e)
        }
    }
}

interface ExceptionListener {
    fun uncaughtException(thread: Thread, throwable: Throwable)
}
