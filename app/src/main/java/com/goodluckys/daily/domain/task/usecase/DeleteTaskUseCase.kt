package com.goodluckys.daily.domain.task.usecase

import com.goodluckys.daily.domain.task.Task
import com.goodluckys.daily.domain.task.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun invoke(item: Task) = taskRepository.delete(item)

}