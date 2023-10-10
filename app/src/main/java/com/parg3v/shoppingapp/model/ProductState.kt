package com.parg3v.shoppingapp.model

import com.parg3v.domain.model.DummyProduct
import com.parg3v.domain.model.EmptyProduct
import com.parg3v.domain.model.Product

data class ProductState(
    override val isLoading: Boolean = false,
    override val data: Product = emptyProduct(),
    override val error: String = ""
): State(isLoading, data, error)

fun emptyProduct(): Product = EmptyProduct

fun dummyProduct(): Product = DummyProduct



