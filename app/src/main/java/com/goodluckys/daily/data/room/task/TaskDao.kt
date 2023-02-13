package com.goodluckys.daily.data.room.task

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasksList(): Flow<List<TaskDbEntity>>

    @Query("SELECT * FROM tasks WHERE category_id=:categoryId ")
    fun getTaskListByCategory(categoryId: Int): Flow<List<TaskDbEntity>>

    @Query("SELECT * FROM tasks WHERE task_id=:id")
    fun getTask(id:Int) : TaskDbEntity

    @Insert()
    fun createTask(task: TaskDbEntity)

    @Delete()
    fun deleteTask(task: TaskDbEntity)

    @Update()
    fun updateTask(task: TaskDbEntity)
}