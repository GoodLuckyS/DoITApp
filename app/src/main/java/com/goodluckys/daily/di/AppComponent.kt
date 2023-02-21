package com.goodluckys.daily.di

import android.app.Application
import com.goodluckys.daily.di.util.ApplicationScope
import com.goodluckys.daily.presentation.ViewModelFactory
import com.goodluckys.daily.presentation.screens.main.MainScreenFragment
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: MainScreenFragment)
    fun viewModelFactory(): ViewModelFactory


    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ): AppComponent

    }

}