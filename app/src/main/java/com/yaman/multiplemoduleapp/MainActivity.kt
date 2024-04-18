package com.yaman.multiplemoduleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yaman.pdf_viewer.ui.PdfViewerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            demoPdfViewer()
        }
    }

    private fun demoPdfViewer() {
        startActivity(Intent(this, PdfViewerActivity::class.java))
    }

}




