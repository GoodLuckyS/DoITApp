package com.goodluckys.daily.presentation.objects

import android.annotation.SuppressLint
import com.goodluckys.core.ui.R.drawable
import com.goodluckys.daily.R.*
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.domain.DrawableSettings

@SuppressLint("ResourceAsColor")
val generalCategory = Category(
    title = "General",
    drawableSettings = DrawableSettings(
        backgroundId = drawable.gradient_background_red,
        imageId = drawable.baseline_search_24
    )
)