package com.goodluckys.daily.presentation.entity

import androidx.annotation.DrawableRes
import com.goodluckys.daily.domain.task.Task
import com.goodluckys.daily.domain.task.TaskWithSettings
import com.goodluckys.daily.presentation.base.BaseUIEntity

data class TaskUI(
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    override val id: Int = Task.UNDEFINED_ID,
    var categoryId: Int = Task.UNDEFINED_ID,
    @DrawableRes
    val backgroundId: Int = Task.UNDEFINED_ID,
    @DrawableRes
    val imageId: Int = Task.UNDEFINED_ID,
) : BaseUIEntity<Int> {
    fun toDomain() = Task(
        title = title,
        categoryId = categoryId,
        description = description,
        isCompleted = isCompleted,
        id = id
    )
}

fun List<TaskWithSettings>.toUI() = this.map {
    it.toUI()
}

fun TaskWithSettings.toUI() = TaskUI(
    id = task.id,
    categoryId = task.categoryId,
    title = task.title,
    description = task.description,
    isCompleted = task.isCompleted,
    backgroundId = drawableSettings.backgroundId,
    imageId = drawableSettings.imageId
)

