package com.goodluckys.daily.data.room.category

import androidx.room.*


@Entity(
    tableName = "categories",
    indices = [
        Index("title")
    ]
)
data class CategoryDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @Embedded val drawableSettings: DrawableSettingsTuple
    )





