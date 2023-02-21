package com.goodluckys.daily.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import com.goodluckys.daily.databinding.CategoryBinding
import com.goodluckys.daily.presentation.base.BaseAdapter
import com.goodluckys.daily.presentation.entity.CategoryUI

class CategoryListAdapter : BaseAdapter<CategoryUI, CategoryBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CategoryUI> {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(
            CategoryBinding.inflate(inflater, parent, false)
        )
    }

    class CategoryViewHolder(private val binding: CategoryBinding) : BaseViewHolder<CategoryUI>(binding) {
        override fun bind(item: CategoryUI) = with(binding) {
            tvTitle.text = item.title
            background.setBackgroundResource(item.backgroundId)
            ivIcon.setImageResource(item.imageId)
            root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }

    }

}