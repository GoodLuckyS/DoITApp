package com.goodluckys.daily.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodluckys.daily.databinding.TaskBinding
import com.goodluckys.daily.domain.task.Task
import com.goodluckys.daily.presentation.objects.TaskDiffCallback

class TaskListAdapter() : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(TaskBinding.inflate(inflater, parent, false))
    }

    var onLongClickListener: ((item: Task) -> Unit)? = null
    var onClickListener: ((item: Task) -> Unit)? = null

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.onLongClickListener = onLongClickListener
        holder.onClickListener = onClickListener
    }

    class TaskViewHolder(private val binding: TaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var onLongClickListener: ((item: Task) -> Unit)? = null
        var onClickListener: ((item: Task) -> Unit)? = null

        fun bind(item: Task) = with(binding) {
            tvTask.text = item.title
        }

    }

}