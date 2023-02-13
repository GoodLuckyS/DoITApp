package com.goodluckys.daily.data.room.category

import com.goodluckys.daily.data.BaseRepository
import com.goodluckys.daily.data.mappers.toData
import com.goodluckys.daily.data.mappers.toDomain
import com.goodluckys.daily.domain.SimpleResponse
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.domain.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
) : CategoryRepository, BaseRepository(){

    override suspend fun getAll(): SimpleResponse<Flow<List<Category>>> = doRequest {
        categoryDao.getCategoriesList().map { it.toDomain() }
    }

    override suspend fun getItem(id: Int): SimpleResponse<Category> = doRequest {
        categoryDao.getCategory(id).toDomain()
    }

    override suspend fun delete(item: Category): SimpleResponse<String> = doRequest {
        categoryDao.deleteCategory(item.toData()).toString()
    }

    override suspend fun create(item: Category): SimpleResponse<String> = doRequest {
        categoryDao.createCategory(item.toData()).toString()
    }

}