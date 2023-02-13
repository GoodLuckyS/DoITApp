package com.goodluckys.daily.domain.task

import com.goodluckys.daily.domain.SimpleResponse
import com.goodluckys.daily.domain.base.BaseDatabaseRepository
import kotlinx.coroutines.flow.Flow

interface TaskRepository : BaseDatabaseRepository<Task> {

    suspend fun getTaskListByCategory(id:Int) : SimpleResponse<Flow<List<Task>>>

}