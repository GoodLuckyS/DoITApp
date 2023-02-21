package com.goodluckys.daily.domain.task

import com.goodluckys.daily.domain.DrawableSettings

data class TaskWithSettings(
    val task: Task,
    val drawableSettings: DrawableSettings,
)