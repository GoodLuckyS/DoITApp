package com.goodluckys.daily.domain.category.usecase

import com.goodluckys.daily.domain.category.CategoryRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    suspend fun invoke(categoryId: Int) = categoryRepository.getItem(categoryId)

}