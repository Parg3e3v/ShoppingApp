package com.parg3v.shoppingapp.model

import com.parg3v.domain.model.Product

data class ProductsListState(
    override val isLoading: Boolean = false,
    override val data: List<Product> = emptyList(),
    val category: String = "all",
    override val error: String = ""
) : State(isLoading, data, error) {
    companion object {
        fun withDummy(count: Int): ProductsListState {
            return ProductsListState(data = List(count) { _ -> dummyProduct() })
        }
    }
}



