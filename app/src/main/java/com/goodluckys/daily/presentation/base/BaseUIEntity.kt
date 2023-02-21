package com.goodluckys.daily.presentation.base

interface BaseUIEntity<T> {
    val id: T
    override fun equals(other: Any?): Boolean

}