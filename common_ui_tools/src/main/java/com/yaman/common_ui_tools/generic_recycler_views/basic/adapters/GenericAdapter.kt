package com.yaman.common_ui_tools.generic_recycler_views.basic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.yaman.library_tools.app_utils.generic_recycler_view.recycler_view_homogenous.view_holders.BaseItemCallback
import com.yaman.library_tools.app_utils.generic_recycler_view.recycler_view_homogenous.view_holders.BaseViewHolder

/** Generic Adapter For Homogenous Recycler View */
abstract class GenericAdapter<T : Any>(@LayoutRes val layoutId: Int) : ListAdapter<T, BaseViewHolder<T>>(BaseItemCallback<T>()) {

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

    fun updateList(list: MutableList<T>) {
        items.clear()
        items.addAll(list)
//        notifyDataSetChanged() // DiffUtils
    }

    fun addMoreList(list: MutableList<T>) {
        val currentSize = this.items.size
        this.items.addAll(list)
        val newSize = this.items.size
//        notifyItemRangeChanged(currentSize, newSize) // DiffUtils
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



