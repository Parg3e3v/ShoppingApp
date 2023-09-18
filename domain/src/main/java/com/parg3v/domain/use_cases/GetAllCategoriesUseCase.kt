package com.parg3v.domain.use_cases

import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(): Flow<ResultOf<List<String>>> = flow {
        try {
            emit(ResultOf.Loading())
            val categories = productsRepository.getAllCategories()
            emit(ResultOf.Success(categories))
        } catch (e: IOException) {
            emit(ResultOf.Failure("Couldn't get categories list"))
        }
    }
}