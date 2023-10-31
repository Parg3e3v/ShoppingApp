package com.parg3v.shoppingapp.model

import com.parg3v.domain.model.Product
import com.parg3v.shoppingapp.utils.UiText

data class ProductsListState(
    override val isLoading: Boolean = false,
    override val data: List<Product> = emptyList(),
    val category: UiText = UiText.DynamicString("all"),
    override val error: UiText = UiText.DynamicString("")
) : State(isLoading, data, error) {
    companion object {
        fun withDummy(count: Int): ProductsListState {
            return ProductsListState(data = List(count) { _ -> dummyProduct() })
        }
    }
}



