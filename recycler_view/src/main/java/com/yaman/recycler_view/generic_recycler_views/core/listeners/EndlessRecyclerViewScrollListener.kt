package com.yaman.recycler_view.generic_recycler_views.core.listeners

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!loading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
            loading = true
            onLoadMore(totalItemCount)
        }
    }

    fun onLoadFinished() {
        loading = false
    }

    abstract fun onLoadMore(totalItemsCount: Int)
}
