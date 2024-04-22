package com.yaman.recycler_view.generic_recycler_views.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yaman.recycler_view.generic_recycler_views.core.view_holders.BaseItemCallback
import com.yaman.recycler_view.generic_recycler_views.core.view_holders.BaseViewHolder
import kotlin.math.min

/** Generic Adapter For Homogenous Recycler View with Pagination */
abstract class GenericAdapterPagination<T : Any>(
    @LayoutRes val layoutId: Int,
    private val loadMoreListener: (currentPage: Int) -> Unit // Callback to load more data
) : ListAdapter<T, BaseViewHolder<T>>(BaseItemCallback<T>()) {

    private var currentPage: Int = 0
    private var items = mutableListOf<T>()
    private var isLoading = false

    abstract fun onBindViewHold(holder: BaseViewHolder<T>, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        onBindViewHold(holder, position)
    }

    override fun getItemViewType(position: Int) = layoutId

    override fun getItemCount(): Int {
        return items.size
    }

    /* Required for pagination to work */
    fun setRecyclerViewScrollListener(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                if (layoutManager != null && !isLoading) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition =
                        (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                            ?: 0
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && !isLoading) {
                        isLoading = true
                        loadMoreListener.invoke(currentPage)
                    }
                }
            }
        })
    }

    // Load initial data (first page)
    fun updateInitialData(list: MutableList<T>, pageSize: Int) {
        val initialData = list.subList(0, min(pageSize, list.size))
        currentPage = 0
        items.clear()
        items.addAll(initialData)
    }

    fun addMoreList(list: MutableList<T>, pageSize: Int, currentPage: Int) {
        this.currentPage = currentPage + 1
        val startIndex = this.currentPage * pageSize
        val endIndex = min(startIndex + pageSize, list.size) // Ensure endIndex doesn't exceed data size

        // Load more data
        if (startIndex < list.size) {
            val newData = list.subList(startIndex, endIndex)
            val insertPosition = items.size // Position where new items will be inserted
            this.items.addAll(newData)
            notifyItemRangeInserted(insertPosition, newData.size)
        }

        // Reset isLoading flag
        isLoading = false
    }


    override fun getItem(position: Int): T {
        return items[position]
    }

    fun addItem(position: Int) {
        items.removeAt(position)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
    }
}
