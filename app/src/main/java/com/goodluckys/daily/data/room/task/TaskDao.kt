package com.goodluckys.daily.data.room.task

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasksList(): Flow<List<TaskDbEntity>>

    @Query("SELECT * FROM tasks JOIN categories ON id =tasks.category_id")
    fun getTaskWithDrawableSettingsList(): Flow<List<TaskWithSettingsTuple>>

    @Query("SELECT * FROM tasks JOIN categories ON id =tasks.category_id AND tasks.category_id=:categoryId")
    fun getTaskListByCategory(categoryId: Int): Flow<List<TaskWithSettingsTuple>>

    @Query("SELECT * FROM tasks WHERE task_id=:id")
    fun getTask(id: Int): TaskDbEntity

    @Insert()
    fun createTask(task: TaskDbEntity)

    @Delete()
    fun deleteTask(task: TaskDbEntity)

    @Update()
    fun updateTask(task: TaskDbEntity)

}