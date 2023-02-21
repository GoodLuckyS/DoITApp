package com.goodluckys.daily.domain

import androidx.annotation.DrawableRes

data class DrawableSettings (
    @DrawableRes
    val backgroundId : Int,
    @DrawableRes
    val imageId: Int,
)