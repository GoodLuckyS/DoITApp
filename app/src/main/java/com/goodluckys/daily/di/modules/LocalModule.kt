package com.goodluckys.daily.di.modules

import android.app.Application
import com.goodluckys.daily.data.room.category.CategoryRepositoryImpl
import com.goodluckys.daily.data.room.task.TaskRepositoryImpl
import com.goodluckys.daily.data.room.AppDatabase
import com.goodluckys.daily.data.room.category.CategoryDao
import com.goodluckys.daily.data.room.task.TaskDao
import com.goodluckys.daily.di.util.ApplicationScope
import com.goodluckys.daily.domain.category.CategoryRepository
import com.goodluckys.daily.domain.task.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface LocalModule {

    @ApplicationScope
    @Binds
    fun bindTaskCategoryRepository(impl: CategoryRepositoryImpl) : CategoryRepository

    @ApplicationScope
    @Binds
    fun bindTaskRepository(impl: TaskRepositoryImpl) : TaskRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideTaskCategoryListDao(application: Application) : CategoryDao {
            return AppDatabase.getDatabase(application).taskCategoryDao()
        }

        @ApplicationScope
        @Provides
        fun provideTaskListDao(application: Application) : TaskDao {
            return AppDatabase.getDatabase(application).tasksDao()
        }


    }

}