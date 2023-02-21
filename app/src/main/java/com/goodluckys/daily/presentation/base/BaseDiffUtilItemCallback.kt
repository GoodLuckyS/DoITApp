package com.goodluckys.daily.presentation.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilItemCallback<T : BaseUIEntity<S>, S> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}