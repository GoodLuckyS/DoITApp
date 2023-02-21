package com.goodluckys.daily.presentation.screens.category

import androidx.lifecycle.viewModelScope
import com.goodluckys.daily.domain.task.usecase.DeleteTaskUseCase
import com.goodluckys.daily.domain.task.usecase.GetTaskByCategoryUseCase
import com.goodluckys.daily.domain.task.usecase.GetTaskWithSettingsList
import com.goodluckys.daily.presentation.base.BaseViewModel
import com.goodluckys.daily.presentation.entity.TaskUI
import com.goodluckys.daily.presentation.entity.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val getTaskByCategoryUseCase: GetTaskByCategoryUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : BaseViewModel() {

    private val _uiState = MutableUIStateFlow<String>()
    val uiState = _uiState.asStateFlow()

    private var _taskList =
        MutableSharedFlow<List<TaskUI>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val taskList: SharedFlow<List<TaskUI>> = _taskList.asSharedFlow()

    fun getTaskList(id: Int) {
        _taskList.emitRequest(_uiState, {
            getTaskByCategoryUseCase.invoke(id)
        }) { it.toUI() }
    }

    fun deleteTask(it: TaskUI) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase.invoke(it.toDomain())
        }
    }

}