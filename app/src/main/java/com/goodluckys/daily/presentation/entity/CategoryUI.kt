package com.goodluckys.daily.presentation.entity

import com.goodluckys.daily.domain.DrawableSettings
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.presentation.base.BaseUIEntity

data class CategoryUI(
    val title: String,
    val backgroundId : Int,
    val imageId:Int,
    override val id: Int = Category.UNDEFINED_ID,
) : BaseUIEntity<Int> {

    override fun toString(): String {
        return title
    }

    fun toDomain()= Category(
        title=title,
        drawableSettings = DrawableSettings(
            backgroundId = backgroundId,
            imageId = imageId
        )
    )
}

fun List<Category>.toUI() = this.map {
    it.toUI()
}

fun Category.toUI() = CategoryUI(
    id = id,
    title = title,
    backgroundId = drawableSettings.backgroundId,
    imageId = drawableSettings.imageId
)

