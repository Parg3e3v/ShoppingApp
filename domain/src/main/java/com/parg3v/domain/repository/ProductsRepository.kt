package com.parg3v.domain.repository

import com.parg3v.domain.model.Product

interface ProductsRepository {

    suspend fun getAllProducts(): List<Product>
}