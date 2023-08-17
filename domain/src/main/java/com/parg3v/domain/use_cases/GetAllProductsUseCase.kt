package com.parg3v.domain.use_cases

import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.model.Product
import com.parg3v.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(): Flow<ResultOf<List<Product>>> = flow{
        try {
            emit(ResultOf.Loading())
            val products = productsRepository.getAllProducts()
            emit(ResultOf.Success(products))
        }catch (e: IOException){
            emit(ResultOf.Failure( "Something went wrong"))
        }
    }
}