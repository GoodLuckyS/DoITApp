package com.goodluckys.daily

import android.app.Application
import com.goodluckys.daily.di.DaggerAppComponent


class MainApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

}