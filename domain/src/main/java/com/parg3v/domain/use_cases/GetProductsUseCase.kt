package com.parg3v.domain.use_cases

import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.model.Product
import com.parg3v.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(category: String): Flow<ResultOf<List<Product>>> = flow{
        try {
            emit(ResultOf.Loading())
            val products = productsRepository.getProducts(category)
            emit(ResultOf.Success(products))
        }catch (e: IOException){
            emit(ResultOf.Failure( "Couldn't get products by category"))
        }
    }
}