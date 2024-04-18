package com.yaman.multiplemoduleapp

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.yaman.pdf_viewer.ui.PdfViewerActivity
import com.yaman.runtime_permissions.coroutine_permissions.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
//            demoPdfViewer()
            demoRuntimePermissions()
        }
    }

    private fun demoRuntimePermissions() {
        CoroutineScope(Dispatchers.IO).launch {
            PermissionManager.requestPermissions(this@MainActivity, 123, Manifest.permission.CAMERA, Manifest.permission.BLUETOOTH)
        }
    }

    private fun demoPdfViewer() {
        startActivity(Intent(this, PdfViewerActivity::class.java))
    }

}




