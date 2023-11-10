package com.yaman.common_ui_tools.generic_recycler_views.basic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.yaman.common_ui_tools.generic_recycler_views.basic.model.RecyclerViewData
import com.yaman.library_tools.app_utils.generic_recycler_view.recycler_view_homogenous.view_holders.BaseViewHolder

abstract class GenericMultipleViewAdapter<T : Any>(private val recyclerViewDataList: List<RecyclerViewData>) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    //Custom Methods
    abstract fun onBindViewHold(holder: BaseViewHolder<T>, position: Int, viewType: Int)
    abstract fun getViewType(position: Int): Int

    //Custom List
    private var items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        if (recyclerViewDataList.isNotEmpty()) {
            for (recyclerViewData in recyclerViewDataList) {
                if (viewType == recyclerViewData.viewType) {
                    val binding = DataBindingUtil.inflate<ViewDataBinding>(
                        LayoutInflater.from(parent.context),
                        recyclerViewData.layoutId,
                        parent,
                        false
                    )
                    return BaseViewHolder(binding)
                }
            }
        }
        throw Exception("RecyclerViewData (Model) no layout found in -> recyclerViewData -> layoutIds (List)")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        onBindViewHold(holder, position, holder.itemViewType)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return getViewType(position)
    }

    fun getItem(position: Int): T {
        return items[position]
    }

}