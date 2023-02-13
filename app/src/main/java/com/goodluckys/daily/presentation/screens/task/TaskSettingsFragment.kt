package com.goodluckys.daily.presentation.screens.task

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goodluckys.daily.R
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

    override fun initialize() {
        adapter = DropDownAdapter(
            requireContext(),
            R.layout.soap,
            R.id.superSoap
        )
        binding.categorySelector.setAdapter(adapter)
    }

    override fun setupListeners() {

        var categoryId = -1

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
                adapter.addAll(it)
                adapter.notifyDataSetChanged()
            }

        viewModel.uiState.collectUIState(
            onError = {
                Toast.makeText(this.context, "ERROR", Toast.LENGTH_SHORT).show()
                Log.e("SOAP","Ошибочка в не главном")
            },
            onSuccess = {
                Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        )
    }



}