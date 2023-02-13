package com.goodluckys.daily.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodluckys.daily.domain.SimpleResponse
import com.goodluckys.daily.presentation.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    @Suppress("FunctionName")
    protected fun <T> MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())


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
                Log.e("SOAP","Произошла ошибочка")
            }
        }
    }

    protected fun <T> MutableSharedFlow<T>.emitRequest(

        state: MutableStateFlow<UIState<String>>,
        _response: suspend () -> SimpleResponse<Flow<T>>,
    ) {
        state.value = UIState.Loading()
        viewModelScope.launch {
            when (val response = _response.invoke()) {
                is SimpleResponse.Success -> {
                    this@emitRequest.emitAll(response.value)
                }
                is SimpleResponse.Error -> {
                    state.value = UIState.Error(response.value)
                }
            }
        }

    }

}