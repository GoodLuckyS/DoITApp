package com.goodluckys.daily.domain.task.usecase

import com.goodluckys.daily.domain.task.Task
import com.goodluckys.daily.domain.task.TaskRepository
import javax.inject.Inject

class GetTaskByCategoryUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun invoke(id:Int) = taskRepository.getTaskListByCategory(id = id)

}