package com.goodluckys.daily.presentation.screens.task

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goodluckys.daily.databinding.FragmentTaskSettingsBinding
import com.goodluckys.daily.presentation.adapter.DropDownAdapter
import com.goodluckys.daily.presentation.base.BaseFragment
import com.goodluckys.daily.presentation.screens.main.MainScreenViewModel

class TaskSettingsFragment : BaseFragment<FragmentTaskSettingsBinding>(
    FragmentTaskSettingsBinding::inflate
) {

    override val viewModel: MainScreenViewModel by viewModels {
        appComponent.viewModelFactory()
    }
    private lateinit var adapter: DropDownAdapter

    override fun setupListeners() {

        var categoryId = -1 //TODO CONST

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.categorySelector.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                categoryId = it.id
            }
        }
        binding.btnSave.setOnClickListener {
            viewModel.createTask(
                categoryId = categoryId,
                title = binding.edTitle.text.toString(),
                description = binding.edTitle.text.toString()
            )
        }
    }

    override fun setupSubscribes() {


        viewModel.categoryList.observe {
            if (it.isEmpty()) viewModel.setGeneralCategory()
            adapter = DropDownAdapter(context = requireContext(), data = it)
            binding.categorySelector.setAdapter(adapter)
            adapter.notifyDataSetChanged()
        }

        viewModel.uiState.collectUIState(
            onError = {
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                findNavController().popBackStack()
            }
        )
    }
}