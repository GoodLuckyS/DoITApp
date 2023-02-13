package com.goodluckys.daily.data

import com.goodluckys.daily.domain.SimpleResponse

abstract class BaseRepository {

    suspend fun <T> doRequest(request: suspend () -> T): SimpleResponse<T> {
        return try {
            SimpleResponse.Success(value = request())
        } catch (exception: Exception) {
            SimpleResponse.Error(value = exception.localizedMessage ?: "Error Occurred!")
        }
    }

}