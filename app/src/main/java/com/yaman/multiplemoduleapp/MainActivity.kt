package com.yaman.multiplemoduleapp

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.yaman.pdf_viewer.ui.PdfViewerActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceFunctions()
    }

    private fun serviceFunctions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }

        findViewById<Button>(R.id.startService).setOnClickListener {
//            PdfViewer.openPdfDocumentPicker(this)
            startActivity(Intent(this, PdfViewerActivity::class.java))
//            openPdfDocumentPicker(this)
            /*Intent(this,RunningService::class.java).also {
                it.action = BaseService.Action.START.toString()
//                it.putExtra(BaseService.NOTIFICATION_DATA,
//                    BaseService.NotificationData("running_channel")
//                )
                startService(it)
            }*/
        }

        findViewById<Button>(R.id.stopService).setOnClickListener {
            /*Intent(this,RunningService::class.java).also {
                it.action = BaseService.Action.START.toString()
                stopService(it)
            }*/
        }
    }



}




