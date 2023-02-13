package com.goodluckys.daily.data.room.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "categories",
    indices = [
        Index("title", unique = true)
    ]
)
class CategoryDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "color_id")
    val colorId: Int,
    @ColumnInfo(name = "image_id")
    val imageId: Int,
    @ColumnInfo(name = "title", collate = ColumnInfo.NOCASE)
    val title: String,
)




