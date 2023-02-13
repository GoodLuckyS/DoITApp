package com.goodluckys.daily.presentation.screens.category

import androidx.lifecycle.viewModelScope
import com.goodluckys.core.ui.R
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.domain.category.usecase.CreateCategoryUseCase
import com.goodluckys.daily.presentation.UIState
import com.goodluckys.daily.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategorySettingsViewModel @Inject constructor(
    private val createTaskCategory: CreateCategoryUseCase,
) : BaseViewModel() {

    private val   _uiState = MutableUIStateFlow<String>()
    val uiState = _uiState.asStateFlow()

    fun soapAdd(title: String) {
        _uiState.value = UIState.Loading()
        val it = Category(
            title = title,
            1,
            imageId = R.color.purple_200
        )
        viewModelScope.launch {
            try {
                createTaskCategory.invoke(it)
                _uiState.value = UIState.Success(data = SUCCESS_MESSAGE)
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message.toString())
            }
        }
    }

    companion object {
        const val SUCCESS_MESSAGE = "Success"
    }

}