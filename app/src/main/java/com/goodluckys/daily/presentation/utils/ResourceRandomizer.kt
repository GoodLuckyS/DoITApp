package com.goodluckys.daily.presentation.utils

class ResourceRandomizer {

    private val colorsList = listOf(
        com.goodluckys.core.ui.R.drawable.gradient_background_green,
        com.goodluckys.core.ui.R.drawable.gradient_background_blue,
        com.goodluckys.core.ui.R.drawable.gradient_background_red,
        com.goodluckys.core.ui.R.drawable.gradient_background_yellow,
        com.goodluckys.core.ui.R.drawable.gradient_background_purple
    )

    private val iconsList = listOf(
        com.goodluckys.core.ui.R.drawable.baseline_games_24,
        com.goodluckys.core.ui.R.drawable.baseline_person_24,
        com.goodluckys.core.ui.R.drawable.baseline_home_24,
        com.goodluckys.core.ui.R.drawable.baseline_palette_24,
        com.goodluckys.core.ui.R.drawable.baseline_book_24,
        com.goodluckys.core.ui.R.drawable.baseline_search_24,
    )

    fun getRandomColor(): Int = colorsList.random()
    fun getRandomIcon(): Int = iconsList.random()

}