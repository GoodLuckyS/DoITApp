package com.goodluckys.daily.presentation.screens.main

import androidx.lifecycle.viewModelScope
import com.goodluckys.daily.domain.DrawableSettings
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.domain.category.usecase.CreateCategoryUseCase
import com.goodluckys.daily.domain.category.usecase.GetCategoryListUseCase
import com.goodluckys.daily.domain.category.usecase.GetCategoryUseCase
import com.goodluckys.daily.domain.task.usecase.CreateTaskUseCase
import com.goodluckys.daily.domain.task.usecase.GetTaskWithSettingsList
import com.goodluckys.daily.presentation.base.BaseViewModel
import com.goodluckys.daily.presentation.entity.CategoryUI
import com.goodluckys.daily.presentation.entity.TaskUI
import com.goodluckys.daily.presentation.entity.toUI
import com.goodluckys.daily.presentation.objects.generalCategory
import com.goodluckys.daily.presentation.utils.ResourceRandomizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getTaskWithSettingsList: GetTaskWithSettingsList,
    private val createTaskUseCase: CreateTaskUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
) : BaseViewModel() {

    private var _taskList =
        MutableSharedFlow<List<TaskUI>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val taskList: SharedFlow<List<TaskUI>> = _taskList

    private var _categoryList =
        MutableSharedFlow<List<CategoryUI>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val categoryList: SharedFlow<List<CategoryUI>> = _categoryList

    private val _uiState = MutableUIStateFlow<String>()
    val uiState = _uiState.asStateFlow()

    init {
        _categoryList.emitRequest(_uiState, {
            getCategoryListUseCase.invoke()
        }){it.toUI()}

        _taskList.emitRequest(_uiState, {
            getTaskWithSettingsList.invoke()
        }) { it.toUI() }
    }

    fun createCategory(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            createCategoryUseCase.invoke(
                Category(
                    title = title,
                    DrawableSettings(
                        backgroundId = ResourceRandomizer().getRandomColor(),
                        imageId = ResourceRandomizer().getRandomIcon()
                    )
                )
            ).collectRequest(_uiState)
        }
    }

    fun createTask(
        categoryId: Int,
        title: String,
        description: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            createTaskUseCase.invoke(
                TaskUI(
                    categoryId = categoryId,
                    title = title,
                    description = description,
                ).toDomain()
            ).collectRequest(_uiState)
        }
    }

    fun resetUIState() {
        _uiState.reset()
    }

    fun setGeneralCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            createCategoryUseCase.invoke(generalCategory)
        }
    }
}