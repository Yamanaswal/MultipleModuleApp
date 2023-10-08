package com.yaman.pdf_viewer.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.recyclerview.widget.RecyclerView
import com.yaman.pdf_viewer.adapters.PdfViewerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.Executors

private const val OPEN_PDF_DOCUMENT_REQUEST_CODE = 0x33
private const val TAG = "PdfViewer"

suspend fun renderSinglePage(filePath: String, width: Int) = withContext(Dispatchers.IO) {
    PdfRenderer(
        ParcelFileDescriptor.open(
            File(filePath),
            ParcelFileDescriptor.MODE_READ_ONLY
        )
    ).use { renderer ->
        renderer.openPage(0).renderAndClose(width)
    }
}

fun openPdfDocument(
    context: Context,
    documentUri: Uri,
    recyclerView: RecyclerView,
    pageWidth: Int
) {
    /**
     * It may be tempting to use `use` here, but [PdfRenderer] expects to take ownership
     * of the [FileDescriptor], and, if we did use `use`, it would be auto-closed at the
     * end of the block, preventing us from rendering additional pages.
     */
    val fileDescriptor = context.contentResolver.openFileDescriptor(documentUri, "r") ?: return

    // This is the PdfRenderer we use to render the PDF.
    val pdfRenderer = PdfRenderer(fileDescriptor)
    recyclerView.adapter = PdfViewerAdapter(pdfRenderer, pageWidth)
}


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
    activity.startActivityForResult(intent, OPEN_PDF_DOCUMENT_REQUEST_CODE)
}

fun splitPdfIntoSections(pdfRenderer: PdfRenderer): List<PdfSection> {
    val pdfSections = mutableListOf<PdfSection>()
    val pageCount = pdfRenderer.pageCount
    var page = 1
    val pagesPerSection = 5  // Adjust this based on your desired section size

    for (i in 0 until pageCount step pagesPerSection) {
        val sectionStart = i
        val sectionEnd = minOf(i + pagesPerSection, pageCount)
        val section = PdfSection(sectionStart, sectionEnd, page)
        pdfSections.add(section)
        page++
    }

    return pdfSections
}

data class PdfSection(val startPage: Int, val endPage: Int, val page: Int)
