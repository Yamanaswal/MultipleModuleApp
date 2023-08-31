package com.yaman.multiplemoduleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yaman.network_module.NetworkCore
import com.yaman.network_module.interfaces.BaseCallback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val retrofitClient = NetworkCore.getNetworkService(this,"")
       val api = retrofitClient.create(Api::class.java)
       api.getData().enqueue(object : BaseCallback<String>(this) {
            override fun onResponse(call: Call<String>, response: Response<String>) {

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                super.onFailure(call, t)
            }
        })
    }

}

interface Api {
    fun getData(): Call<String>
}