package com.parg3v.data.remote

import com.parg3v.data.config.NetworkConfig
import com.parg3v.data.model.ProductModel
import retrofit2.http.GET

interface MyApi {

    @GET(NetworkConfig.PATH_PRODUCTS)
    suspend fun getAllProducts(): List<ProductModel>
}