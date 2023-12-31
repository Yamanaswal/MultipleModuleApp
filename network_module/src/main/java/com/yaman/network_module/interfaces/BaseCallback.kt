package com.yaman.network_module.interfaces

import android.content.Context
import android.widget.Toast
import com.yaman.network_module.exceptions.NoConnectivityException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseCallback<T>(private val context: Context) : Callback<T> {

    /**
     * Invoked for a received HTTP response.
     *
     *
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call [Response.isSuccessful] to determine if the response indicates success.
     */
    abstract override fun onResponse(call: Call<T>, response: Response<T>)

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected exception
     * occurred creating the request or processing the response.
     */
    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t is NoConnectivityException) {
            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
        }
    }
}