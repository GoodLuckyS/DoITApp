package com.goodluckys.daily.di.modules

import androidx.lifecycle.ViewModel
import com.goodluckys.daily.di.util.ViewModelKey
import com.goodluckys.daily.presentation.screens.category.CategorySettingsViewModel
import com.goodluckys.daily.presentation.screens.category.CategoryViewModel
import com.goodluckys.daily.presentation.screens.main.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindsMainViewModel(viewModel: MainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategorySettingsViewModel::class)
    fun bindsCategorySettingsViewModel(viewModel: CategorySettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    fun bindsCategoryViewModel(viewModel: CategoryViewModel): ViewModel


}