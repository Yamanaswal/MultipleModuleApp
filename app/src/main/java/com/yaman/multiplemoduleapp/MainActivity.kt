package com.yaman.multiplemoduleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yaman.network_module.NetworkCore
import com.yaman.network_module.models.NetworkConfiguration
import com.yaman.network_module.services.OkHttpClientGenerator
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        networkCoreTesting()
    }

    private fun networkCoreTesting() {

        val service = NetworkCore.createNetworkClient(this, NetworkConfiguration(
            baseUrl = "",
            okHttpClient = OkHttpClientGenerator().createCore(),
//            convertors = arrayListOf(GsonConverterFactory.create(),ScalarsConverterFactory.create())
        ), service = ApiInterface::class.java)

    }





}

class ApiInterface {

}




