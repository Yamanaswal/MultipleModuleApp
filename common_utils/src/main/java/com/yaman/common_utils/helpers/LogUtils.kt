package com.yaman.common_utils.helpers

import android.util.Log

object LogUtils {

    private var isDebuggable = true
        set(value) {
            field = value
        }

    fun e(tag: String, message: String) {
        if (isDebuggable) {
            Log.e(tag, message)
        }
    }

    fun i(tag: String, message: String) {
        if (isDebuggable) {
            Log.i(tag, message)
        }
    }

    fun d(tag: String, message: String) {
        if (isDebuggable) {
            Log.d(tag, message)
        }
    }

    fun w(tag: String, message: String) {
        if (isDebuggable) {
            Log.w(tag, message)
        }
    }

}