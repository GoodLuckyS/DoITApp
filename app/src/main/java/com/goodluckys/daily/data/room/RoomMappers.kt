package com.goodluckys.daily.data.mappers

import com.goodluckys.daily.data.room.category.CategoryDbEntity
import com.goodluckys.daily.data.room.category.DrawableSettingsTuple
import com.goodluckys.daily.data.room.task.TaskDbEntity
import com.goodluckys.daily.domain.task.Task
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.domain.DrawableSettings

fun CategoryDbEntity.toDomain() = Category(
    id = id,
    title = title,
    drawableSettings = DrawableSettings(
        imageId = drawableSettings.imageId,
        backgroundId = drawableSettings.colorId,

    )
)

fun Category.toData() = CategoryDbEntity(
    id = id,
    title = title,
    drawableSettings = DrawableSettingsTuple(
        imageId = drawableSettings.imageId,
        colorId = drawableSettings.backgroundId
    ),
)

fun List<CategoryDbEntity>.toDomain() = this.map {
    it.toDomain()
}

fun TaskDbEntity.toDomain() = Task(
    id = id,
    title = title,
    categoryId = categoryId,
    description = description,
    isCompleted = isCompleted,
)

fun Task.toData() = TaskDbEntity(
    id, categoryId, title, description, isCompleted
)

fun List<TaskDbEntity>.toTask() = this.map {
    it.toDomain()
}//TODO#1 QUESTION:MAPPING

