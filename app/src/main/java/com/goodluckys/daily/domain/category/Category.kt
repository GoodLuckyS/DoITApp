package com.goodluckys.daily.domain.category

import com.goodluckys.daily.domain.DrawableSettings

data class Category(
    val title: String,
    val drawableSettings: DrawableSettings,
    val id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
