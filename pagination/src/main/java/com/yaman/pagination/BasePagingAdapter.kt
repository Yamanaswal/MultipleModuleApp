package com.yaman.pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter

abstract class BasePagingAdapter<T : Any>(@LayoutRes val layoutId: Int) : PagingDataAdapter<T, BaseViewHolder<T>>(BaseItemCallback<T>()) {

    //Custom List
    private var items = mutableListOf<T>()

    //Custom Methods
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

    fun getItemData(position: Int): T {
        return items[position]
    }

    fun addItem(position: Int) {
        items.removeAt(position)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
    }

}
