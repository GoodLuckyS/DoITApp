package com.goodluckys.daily.domain.category

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

data class Category(
    val title: String,
    @ColorInt
    val colorId: Int,
    @DrawableRes
    val imageId: Int,
    val id: Int = UNDEFINED_ID,
) {
    override fun toString(): String {
        return title
    }
    companion object {

        const val UNDEFINED_ID = 0
    }
}
