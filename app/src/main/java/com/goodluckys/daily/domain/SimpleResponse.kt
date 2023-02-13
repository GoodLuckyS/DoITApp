package com.goodluckys.daily.domain

sealed class SimpleResponse<out T> {
    data class Success<out T>(val value: T) : SimpleResponse<T>()
    data class Error(val value: String) : SimpleResponse<Nothing>()
}