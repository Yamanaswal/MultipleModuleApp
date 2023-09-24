package com.yaman.pdf_viewer.base

import android.app.Activity
import android.content.Intent
import com.yaman.pdf_viewer.ui.PdfViewerActivity

object PdfViewer {

    private const val OPEN_DOCUMENT_REQUEST_CODE = 0x33
    private const val TAG = "PdfViewer"

    fun openPdfDocumentPicker(activity: Activity) {
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
        activity.startActivityForResult(intent, OPEN_DOCUMENT_REQUEST_CODE)
    }

}