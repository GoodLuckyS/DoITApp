package com.goodluckys.daily.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodluckys.daily.domain.SimpleResponse
import com.goodluckys.daily.presentation.UIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    @Suppress("FunctionName")
    protected fun <T> MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    fun <T> MutableStateFlow<UIState<T>>.reset() {
        value = UIState.Idle()
    }

    protected fun SimpleResponse<String>.collectRequest(
        state: MutableStateFlow<UIState<String>>,
    ) {
        state.value = UIState.Loading()
        when (this) {
            is SimpleResponse.Success -> {
                state.value = UIState.Success("Success")
            }
            is SimpleResponse.Error -> {
                state.value = UIState.Error(this.value)
            }
        }
    }

    protected fun <T,S> MutableSharedFlow<T>.emitRequest(

        state: MutableStateFlow<UIState<S>>,
        _response: suspend () -> SimpleResponse<Flow<T>>,

    ) {
        state.value = UIState.Loading()
        viewModelScope.launch {
            when (val response = _response()) {
                is SimpleResponse.Success -> {
                    this@emitRequest.emitAll(response.value)
                    state.value = UIState.Idle()
                }
                is SimpleResponse.Error -> {
                    state.value = UIState.Error(response.value)
                }
            }
        }
    } //TODO#5 QUESTION:MAP TO UI

    protected fun <T,M,S> MutableSharedFlow<T>.emitRequest(

        state: MutableStateFlow<UIState<S>>,
        _response: suspend () -> SimpleResponse<Flow<M>>,
        mappedData: ((M) -> T)

        ) {
        state.value = UIState.Loading()
        viewModelScope.launch {
            when (val response = _response()) {
                is SimpleResponse.Success -> {
                    this@emitRequest.emitAll(response.value.map {
                        mappedData(it)
                    })
                    state.value = UIState.Idle()
                }
                is SimpleResponse.Error -> {
                    state.value = UIState.Error(response.value)
                }
            }
        }
    }

}