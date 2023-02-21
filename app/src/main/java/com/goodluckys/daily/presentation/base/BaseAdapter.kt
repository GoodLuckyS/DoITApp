package com.goodluckys.daily.presentation.base

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T : BaseUIEntity<Int>, VB : ViewBinding> :
    ListAdapter<T, BaseAdapter.BaseViewHolder<T>>(BaseDiffUtilItemCallback()) {

    var onClickListener: ((item: T) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.onClickListener = onClickListener
        holder.bind(item)
    }

    abstract class BaseViewHolder<T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        var onClickListener: ((item: T) -> Unit)? = null
        abstract fun bind(item: T)
    }
}


