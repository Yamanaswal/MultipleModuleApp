package com.yaman.recycler_view.generic_recycler_views.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.yaman.recycler_view.generic_recycler_views.core.view_holders.BaseItemCallback
import com.yaman.recycler_view.generic_recycler_views.core.view_holders.BaseViewHolder
import kotlin.math.ceil

/** Generic Adapter For Homogenous Recycler View with Pagination */
abstract class GenericAdapterPagination<T : Any>(
    @LayoutRes val layoutId: Int,
    private val pageSize: Int = 20,
) : ListAdapter<T, BaseViewHolder<T>>(BaseItemCallback<T>()) {

    private var currentPage = 0

    //Custom List
    private var items = mutableListOf<T>()

    //Custom Methods
    abstract fun onBindViewHold(holder: BaseViewHolder<T>, position: Int, item: T)


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
        val startIndex = currentPage * pageSize
        val endIndex = Math.min(startIndex + pageSize, items.size)
        val pageItems = items.subList(startIndex, endIndex)
        val item = pageItems[position]
        onBindViewHold(holder, position, item)
    }

    override fun getItemViewType(position: Int) = layoutId

    override fun getItemCount(): Int {
        val count = (items.size / pageSize.toDouble())
        return ceil(count).toInt()
    }

    fun updateList(list: MutableList<T>) {
        items.clear()
        items.addAll(list)
        // DiffUtils used
    }

    fun updateCurrentPage(currentPage: Int = 0) {
        this.currentPage = currentPage
    }
}



