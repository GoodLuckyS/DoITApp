package com.goodluckys.daily.data.room.category

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getCategoriesList(): Flow<List<CategoryDbEntity>>

    @Query("SELECT * FROM categories WHERE id=:categoryId")
    suspend fun getCategory(categoryId: Int): CategoryDbEntity

    @Insert()
    suspend fun createCategory(taskCategory: CategoryDbEntity)

    @Delete
    suspend fun deleteCategory(category:CategoryDbEntity)

}