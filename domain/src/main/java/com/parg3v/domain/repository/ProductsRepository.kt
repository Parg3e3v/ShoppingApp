package com.parg3v.domain.repository

import com.parg3v.domain.model.Product

interface ProductsRepository {
    suspend fun getProducts(category: String): List<Product>
    suspend fun getAllCategories(): List<String>
    suspend fun getProductById(id: String): Product
}