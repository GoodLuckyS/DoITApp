package com.goodluckys.daily.presentation.screens.main

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodluckys.daily.R
import com.goodluckys.daily.databinding.FragmentMainScreenBinding
import com.goodluckys.daily.presentation.adapter.CategoryListAdapter
import com.goodluckys.daily.presentation.adapter.TaskListAdapter
import com.goodluckys.daily.presentation.base.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainScreenFragment : BaseFragment<FragmentMainScreenBinding>(
    FragmentMainScreenBinding::inflate
) {
    override val viewModel: MainScreenViewModel by viewModels {
        appComponent.viewModelFactory()
    }

    lateinit var categoryListAdapter: CategoryListAdapter
    lateinit var taskListAdapter: TaskListAdapter

    override fun initialize() {
        initBottomSheet()
        setupRecyclerView()
    }

    override fun setupSubscribes() = with(viewModel) {
        categoryList.observe {
            categoryListAdapter.submitList(it)
        }
        taskList.observe {
            taskListAdapter.submitList(it)
        }
        uiState.collectUIState(
            onError = {
                Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
                Log.e("SOAP", "Ошибочка в главном")
            },
            onSuccess = {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun setupListeners() {
        binding.createCategory.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_createCategoryFragment)
        }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_taskSettingsFragment)
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context) // context
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        categoryListAdapter = CategoryListAdapter()
        with(binding) {
            categoriesRc.adapter = categoryListAdapter
            categoriesRc.layoutManager = layoutManager
        }

        categoryListAdapter.onClickListener ={
            val directions = MainScreenFragmentDirections.actionMainScreenFragmentToCategoryGraph(it.id)
            findNavController().navigate(directions)
        }

        taskListAdapter = TaskListAdapter()
        val lm1 = LinearLayoutManager(context)
        lm1.orientation = LinearLayoutManager.VERTICAL
        binding.rcTasks.adapter = taskListAdapter
        binding.rcTasks.layoutManager = lm1

    }

    private fun initBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.contet)
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.isHideable = false
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> binding.fab.show()
                    BottomSheetBehavior.STATE_EXPANDED -> binding.fab.show()
                    else -> {
                        binding.fab.hide()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        }
        )
    }
}

