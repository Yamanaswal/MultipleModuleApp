package com.yaman.pdf_viewer.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.yaman.pdf_viewer.R
import com.yaman.pdf_viewer.adapters.PdfViewerAdapter

class PdfViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)
        openPdfDocumentPicker()
    }

    private fun openPdfDocumentPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            /**
             * It's possible to limit the types of files by mime-type. Since this
             * app displays pages from a PDF file, we'll specify `application/pdf`
             * in `type`.
             * See [Intent.setType] for more details.
             */
            type = "application/pdf"

            /**
             * Because we'll want to use [ContentResolver.openFileDescriptor] to read
             * the data of whatever file is picked, we set [Intent.CATEGORY_OPENABLE]
             * to ensure this will succeed.
             */
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, OPEN_DOCUMENT_REQUEST_CODE)
    }

    private fun openPdfDocument(
        documentUri: Uri,
        recyclerView: RecyclerView,
        pageWidth: Int
    ) {
        /**
         * It may be tempting to use `use` here, but [PdfRenderer] expects to take ownership
         * of the [FileDescriptor], and, if we did use `use`, it would be auto-closed at the
         * end of the block, preventing us from rendering additional pages.
         */
        val fileDescriptor = contentResolver.openFileDescriptor(documentUri, "r") ?: return

        // This is the PdfRenderer we use to render the PDF.
        val pdfRenderer = PdfRenderer(fileDescriptor)
        recyclerView.adapter = PdfViewerAdapter(pdfRenderer, pageWidth)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == OPEN_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also { documentUri ->

                /**
                 * Upon getting a document uri returned, we can use
                 * [ContentResolver.takePersistableUriPermission] in order to persist the
                 * permission across restarts.
                 *
                 * This may not be necessary for your app. If the permission is not
                 * persisted, access to the uri is granted until the receiving Activity is
                 * finished. You can extend the lifetime of the permission grant by passing
                 * it along to another Android component. This is done by including the uri
                 * in the data field or the ClipData object of the Intent used to launch that
                 * component. Additionally, you need to add FLAG_GRANT_READ_URI_PERMISSION
                 * and/or FLAG_GRANT_WRITE_URI_PERMISSION to the Intent.
                 *
                 * This app takes the persistable URI permission grant to demonstrate how, and
                 * to allow us to reopen the last opened document when the app starts.
                 */
                contentResolver.takePersistableUriPermission(
                    documentUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                val rv = findViewById<RecyclerView>(R.id.recyclerView)
                openPdfDocument(documentUri, rv, getScreenWidth(this))
            }
        } else {
            finish()
        }
    }

    private fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

}


private const val OPEN_DOCUMENT_REQUEST_CODE = 0x33
private const val TAG = "PdfViewer"
