package com.goodluckys.daily.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.goodluckys.daily.databinding.TaskBinding
import com.goodluckys.daily.presentation.base.BaseAdapter
import com.goodluckys.daily.presentation.entity.TaskUI

class TaskListAdapter : BaseAdapter<TaskUI, TaskBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TaskUI> {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(TaskBinding.inflate(inflater, parent, false))
    }

    class TaskViewHolder(private val binding: TaskBinding) : BaseViewHolder<TaskUI>(binding) {
        override fun bind(item: TaskUI) = with(binding) {
            tvTask.text = item.title
            binding.superSoapa.setImageResource(item.imageId)
            binding.superSoapa.setBackgroundResource(item.backgroundId)
        }
    }
}