package com.goodluckys.daily.domain.task.usecase

import com.goodluckys.daily.domain.task.TaskRepository
import javax.inject.Inject

class GetTaskListUseCase@Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun invoke() = taskRepository.getAll()

}