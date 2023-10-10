package com.parg3v.data.remote

import com.parg3v.data.config.NetworkConfig
import com.parg3v.data.model.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApi {

    @GET(NetworkConfig.PATH_PRODUCTS)
    suspend fun getAllProducts(): List<ProductModel>

    @GET(NetworkConfig.PATH_PRODUCTS + NetworkConfig.PATH_PRODUCT_CATEGORY + "/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<ProductModel>

    @GET(NetworkConfig.PATH_PRODUCTS + NetworkConfig.PATH_ALL_CATEGORIES)
    suspend fun getAllCategories(): List<String>

    @GET(NetworkConfig.PATH_PRODUCTS + "/{id}")
    suspend fun getProductById(@Path("id") id: String): ProductModel
}