package com.parg3v.shoppingapp.model

import com.parg3v.domain.model.DummyProduct
import com.parg3v.domain.model.Product

data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val category: String = "all",
    val error: String = ""
) {
    companion object {
        fun withDummy(count: Int): ProductListState {
            return ProductListState(products = List(count) { _ -> DummyProduct })
        }
    }
}



