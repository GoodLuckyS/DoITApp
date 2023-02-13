package com.goodluckys.daily.presentation.screens.category

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goodluckys.daily.databinding.FragmentCategorySettingsBinding
import com.goodluckys.daily.presentation.base.BaseFragment


class CategorySettingsFragment : BaseFragment<FragmentCategorySettingsBinding>(
    FragmentCategorySettingsBinding::inflate
) {
    override val viewModel: CategorySettingsViewModel by viewModels {
        appComponent.viewModelFactory()
    }

    override fun setupSubscribes() {
        viewModel.uiState.collectUIState(
            onError = {
                Toast.makeText(this.context, "ERROR", Toast.LENGTH_SHORT).show()
            },
            onSuccess = {
                Toast.makeText(this.context, "SUCA", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun setupListeners() {


    }

}