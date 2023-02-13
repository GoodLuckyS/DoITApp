package com.goodluckys.daily.domain.category.usecase

import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.domain.category.CategoryRepository
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    suspend fun invoke(category: Category) = categoryRepository.create(category)

}