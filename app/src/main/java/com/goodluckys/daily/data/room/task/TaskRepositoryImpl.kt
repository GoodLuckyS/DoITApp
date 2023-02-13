package com.goodluckys.daily.data.room.task

import android.util.Log
import com.goodluckys.daily.data.BaseRepository
import com.goodluckys.daily.data.mappers.toData
import com.goodluckys.daily.data.mappers.toDomain
import com.goodluckys.daily.data.mappers.toTask
import com.goodluckys.daily.domain.SimpleResponse
import com.goodluckys.daily.domain.task.Task
import com.goodluckys.daily.domain.task.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
) : TaskRepository, BaseRepository() {

    override suspend fun getTaskListByCategory(id: Int): SimpleResponse<Flow<List<Task>>> =
        doRequest {
            taskDao.getTaskListByCategory(id).map { it.toTask() }
        }

    override suspend fun getAll(): SimpleResponse<Flow<List<Task>>> = doRequest {
        taskDao.getTasksList().map { it.toTask() }
    }

    override suspend fun getItem(id: Int): SimpleResponse<Task> = doRequest {
        taskDao.getTask(id).toDomain()
    }

    override suspend fun delete(item: Task): SimpleResponse<String> = doRequest {
        taskDao.deleteTask(item.toData()).toString()

    }

    override suspend fun create(item: Task): SimpleResponse<String> = doRequest {
        taskDao.createTask(item.toData()).toString()
    }
}