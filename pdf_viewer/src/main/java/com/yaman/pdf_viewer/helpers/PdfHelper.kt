package com.yaman.pdf_viewer.helpers

import android.content.Context
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

