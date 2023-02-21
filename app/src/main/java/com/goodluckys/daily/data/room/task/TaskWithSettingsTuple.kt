package com.goodluckys.daily.data.room.task

import androidx.room.Embedded
import com.goodluckys.daily.data.room.category.DrawableSettingsTuple
import com.goodluckys.daily.domain.DrawableSettings
import com.goodluckys.daily.domain.task.Task
import com.goodluckys.daily.domain.task.TaskWithSettings

data class TaskWithSettingsTuple(
    @Embedded val taskDbEntity:TaskDbEntity,
    @Embedded val drawableSettingsTuple : DrawableSettingsTuple
)

fun TaskWithSettingsTuple.toDomain() = TaskWithSettings(
    task = Task(
        categoryId = taskDbEntity.categoryId,
        title = taskDbEntity.title,
        description = taskDbEntity.description,
        isCompleted = taskDbEntity.isCompleted,
        id = taskDbEntity.id,
    ),
    drawableSettings = DrawableSettings(
        backgroundId = drawableSettingsTuple.colorId,
        imageId = drawableSettingsTuple.imageId,
    )
)

fun List<TaskWithSettingsTuple>.toDomain() = this.map {
    it.toDomain()
}