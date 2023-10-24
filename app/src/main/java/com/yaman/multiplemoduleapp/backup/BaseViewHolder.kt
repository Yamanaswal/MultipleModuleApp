//package com.yaman.paging
//
//import android.annotation.SuppressLint
//import androidx.databinding.ViewDataBinding
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//
//
///**  Base View Holder **/
//class BaseViewHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
//
//
///**  Base DiffUtils **/
//class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
//    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()
//
//    @SuppressLint("DiffUtilEquals")
//    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
//}