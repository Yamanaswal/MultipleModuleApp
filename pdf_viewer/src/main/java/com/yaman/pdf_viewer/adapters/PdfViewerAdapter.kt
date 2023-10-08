package com.yaman.pdf_viewer.adapters

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.yaman.pdf_viewer.R
import com.yaman.pdf_viewer.helpers.renderAndClose

class PdfViewerAdapter(
    // this would come from the ViewModel so we don't need to recreate it on config change
    // and the VM can close it within onCleared()
    private val renderer: PdfRenderer,
    // this would come from the Activity/Fragment based on the display metrics
    private val pageWidth: Int
) : RecyclerView.Adapter<PdfViewerAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(bitmap: Bitmap) = (itemView as ImageView).setImageBitmap(bitmap)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pdf_page_item, parent, false)
        )

    override fun getItemCount() = renderer.pageCount

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(renderer.openPage(position).renderAndClose(pageWidth))
    }
}