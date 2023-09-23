package com.yaman.multiplemoduleapp

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.yaman.common_ui_tools.generic_services.common.BaseService
import retrofit2.Call

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val retrofitClient = NetworkCore.getNetworkService(this, "")
//        val api = retrofitClient.create(Api::class.java)
//
//        api.getData().enqueue(object : BaseCallback<String>(this) {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                super.onFailure(call, t)
//            }
//        })
//        val items = arrayOf("Item 1", "Item 2", "Item 3")
//        val customAdapter = CustomSpinnerAdapter(this, items)
//
//        val spinner = findViewById<Spinner>(R.id.spinner)
//        spinner.adapter = customAdapter


        serviceFunctions()

    }

    private fun serviceFunctions() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0)
        }

        findViewById<Button>(R.id.startService).setOnClickListener {
            Intent(this,RunningService::class.java).also {
                it.action = BaseService.Action.START.toString()
//                it.putExtra(BaseService.NOTIFICATION_DATA,
//                    BaseService.NotificationData("running_channel")
//                )
                startService(it)
            }
        }

        findViewById<Button>(R.id.stopService).setOnClickListener {
            Intent(this,RunningService::class.java).also {
                it.action = BaseService.Action.START.toString()
                stopService(it)
            }
        }
    }

}

interface Api {
    fun getData(): Call<String>
}
