package com.goodluckys.daily.data.room.category

import androidx.room.ColumnInfo

data class DrawableSettingsTuple(
    @ColumnInfo(name = "color_id")
    val colorId: Int,
    @ColumnInfo(name = "image_id")
    val imageId: Int,
)