package com.goodluckys.daily.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.domain.task.Task
import com.goodluckys.daily.domain.category.usecase.CreateCategoryUseCase
import com.goodluckys.daily.domain.category.usecase.GetCategoryListUseCase
import com.goodluckys.daily.domain.category.usecase.GetCategoryUseCase
import com.goodluckys.daily.domain.task.usecase.CreateTaskUseCase
import com.goodluckys.daily.domain.task.usecase.GetTaskListUseCase
import com.goodluckys.daily.presentation.base.BaseViewModel
import com.goodluckys.daily.presentation.objects.generalCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getTaskListUseCase: GetTaskListUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val createCategory: CreateCategoryUseCase,
) : BaseViewModel() {

    private var _taskList =
        MutableSharedFlow<List<Task>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val taskList: SharedFlow<List<Task>> = _taskList

    private var _categoryList =
        MutableSharedFlow<List<Category>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val categoryList: SharedFlow<List<Category>> = _categoryList.asSharedFlow()

    private val _uiState = MutableUIStateFlow<String>()
    val uiState = _uiState.asStateFlow()

    init {
        _categoryList.emitRequest(_uiState) {
            getCategoryListUseCase.invoke()
        }
        _taskList.emitRequest(_uiState) {
            getTaskListUseCase.invoke()
        }
    }

    fun createTask(
        categoryId: Int,
        title: String,
        description: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            createTaskUseCase.invoke(
                Task(
                    categoryId = categoryId,
                    title = title,
                    description = description,
                )
            ).collectRequest(_uiState)
        }
    }

    fun setGeneralCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            createCategory.invoke(generalCategory)
        }
    }
}