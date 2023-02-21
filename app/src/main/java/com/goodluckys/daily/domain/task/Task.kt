package com.goodluckys.daily.domain.task

data class Task(
    var categoryId: Int = 1,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val id: Int = UNDEFINED_ID,
) {
    companion object {

        const val UNDEFINED_ID = 0
    }
} //TODO createdAt,Discription,expiredAt