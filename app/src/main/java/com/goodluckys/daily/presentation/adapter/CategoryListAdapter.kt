package com.goodluckys.daily.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodluckys.daily.databinding.CategoryBinding
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.presentation.objects.CategoryDiffCallback

class CategoryListAdapter :
    ListAdapter<Category, CategoryListAdapter.TaskCategoryViewHolder>(CategoryDiffCallback())
     {
         var onLongClickListener: ((item: Category) -> Unit)? = null
         var onClickListener: ((item: Category) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskCategoryViewHolder(CategoryBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TaskCategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.onClickListener = onClickListener
        holder.bind(item)
    }

    class TaskCategoryViewHolder(private val binding: CategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var onLongClickListener: ((item: Category) -> Unit)? = null
        var onClickListener: ((item: Category) -> Unit)? = null
        fun bind(item: Category) {
            binding.tvTitle.text = item.title
            if (item.imageId > 10)
                binding.cll.setBackgroundResource(item.imageId)
            binding.root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }


}