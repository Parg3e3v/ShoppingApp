package com.parg3v.shoppingapp.model

data class CategoriesListState(
    val isLoading: Boolean = false,
    val categories: List<String> = emptyList(),
    val error: String = ""
)
