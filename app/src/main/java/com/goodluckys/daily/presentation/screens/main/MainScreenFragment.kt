package com.goodluckys.daily.presentation.screens.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodluckys.daily.R
import com.goodluckys.daily.databinding.FragmentMainScreenBinding
import com.goodluckys.daily.databinding.PartInputBinding
import com.goodluckys.daily.presentation.adapter.CategoryListAdapter
import com.goodluckys.daily.presentation.adapter.TaskListAdapter
import com.goodluckys.daily.presentation.base.BaseFragment
import com.goodluckys.daily.presentation.utils.showToast
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
//            binding.categoriesRc.smoothScrollToPosition(categoryListAdapter.itemCount-1)
        }
        taskList.observe {
            taskListAdapter.submitList(it)

        }

        uiState.collectUIState(
            onSuccess = {
                showToast(it)
            },
            onError = {
                showToast(it)
            },
        ){this.resetUIState()}

    }

    override fun setupListeners() {
        binding.createCategory.setOnClickListener {
            showDialog()
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

        categoryListAdapter.onClickListener = {
            val directions =
                MainScreenFragmentDirections.actionMainScreenFragmentToCategoryGraph(it.id)
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

    private fun showDialog() {
        val dialogBinding = PartInputBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_create_category_title)
            .setMessage(R.string.dialog_create_category_message)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.dialog_positive_button, null)
            .create()
        dialog.setOnShowListener {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                viewModel.createCategory(dialogBinding.edTitle.text.toString())
                dialog.dismiss()
            }
        }
        dialog.show()
    }


}

