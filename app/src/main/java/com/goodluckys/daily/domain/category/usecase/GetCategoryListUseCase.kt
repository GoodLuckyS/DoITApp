package com.goodluckys.daily.domain.category.usecase

import com.goodluckys.daily.domain.category.CategoryRepository
import javax.inject.Inject

class GetCategoryListUseCase@Inject constructor(private val categoryRepository: CategoryRepository) {

   suspend fun invoke() = categoryRepository.getAll()

}