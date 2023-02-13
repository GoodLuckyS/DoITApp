package com.goodluckys.daily.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.goodluckys.daily.data.room.category.CategoryDao
import com.goodluckys.daily.data.room.task.TaskDao
import com.goodluckys.daily.data.room.category.CategoryDbEntity
import com.goodluckys.daily.data.room.task.TaskDbEntity

@Database(
    version = 2,
    entities = [
        CategoryDbEntity::class,
        TaskDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskCategoryDao(): CategoryDao
    abstract fun tasksDao(): TaskDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tasks_info"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}