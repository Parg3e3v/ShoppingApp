package com.parg3v.data.repository

import com.parg3v.data.extensions.toProduct
import com.parg3v.data.remote.MyApi
import com.parg3v.domain.model.Product
import com.parg3v.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val api: MyApi
): ProductsRepository {
    override suspend fun getAllProducts(): List<Product> {
        return api.getAllProducts().map { it.toProduct() }
    }
}