package com.goodluckys.daily.domain.base

import com.goodluckys.daily.domain.SimpleResponse
import kotlinx.coroutines.flow.Flow

interface BaseDatabaseRepository<T> {

    suspend fun getAll(): SimpleResponse<Flow<List<T>>>

    suspend fun getItem(id: Int): SimpleResponse<T>

    suspend fun delete(item: T): SimpleResponse<String>

    suspend fun create(item: T): SimpleResponse<String>

}