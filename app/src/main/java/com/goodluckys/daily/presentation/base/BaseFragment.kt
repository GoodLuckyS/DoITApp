package com.goodluckys.daily.presentation.base


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.goodluckys.daily.MainApplication
import com.goodluckys.daily.presentation.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    val appComponent by lazy {
        (requireActivity().application as MainApplication).appComponent
    }

    protected abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        setupSubscribes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun initialize() {}
    protected open fun setupSubscribes() {}
    protected open fun setupListeners() {}

   private fun collectFlowSafely(
        lifecycleState: Lifecycle.State,
        collect: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                collect()
            }
        }
    }

    protected fun <T> SharedFlow<T>.observe(action: suspend (T) -> Unit) {
        collectFlowSafely(Lifecycle.State.STARTED) {
            this.collect {
                action.invoke(it)
            }
        }
    }

    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        state: ((UIState<T>) -> Unit)? = null,
        onError: ((error: String) -> Unit),
        onSuccess: ((data: T) -> Unit),
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        onReact: (()->Unit)? = null,
    ) {
        collectFlowSafely(lifecycleState) {
            this.collect {
                state?.invoke(it)
                when (it) {
                    is UIState.Idle -> {}
                    is UIState.Loading -> {}
                    is UIState.Error -> {
                        onError.invoke(it.error)
                        onReact?.invoke()
                    }
                    is UIState.Success -> {
                        onSuccess.invoke(it.data)
                        onReact?.invoke()
                    }
                }
            }
        }


    }
}