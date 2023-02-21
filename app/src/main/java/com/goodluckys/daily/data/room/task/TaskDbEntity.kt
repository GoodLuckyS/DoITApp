package com.goodluckys.daily.data.room.task

import androidx.room.*
import com.goodluckys.daily.data.room.category.CategoryDbEntity

@Entity(
    tableName = "tasks",
    indices = [
        Index("category_id"),

    ],
    foreignKeys = [
        ForeignKey(
            entity = CategoryDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class TaskDbEntity(
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "task_title")
    val title: String,
    @ColumnInfo(name = "count")
    val description: String,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean,
//    @ColumnInfo(name = "is_daily")
//    val isDaily: Boolean,
//    @ColumnInfo(name = "created_at")
//    val createdAt: String,
//    @ColumnInfo(name = "expire_at")
//    val expireAt: String,

    )