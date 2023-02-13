package com.goodluckys.daily.di

import com.goodluckys.daily.di.modules.LocalModule
import com.goodluckys.daily.di.modules.ViewModelModule
import dagger.Module

@Module(
    includes = [
        LocalModule::class,
        ViewModelModule::class
    ]
)
interface AppModule {

}