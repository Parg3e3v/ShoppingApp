package com.parg3v.shoppingapp.model

data class CategoriesListState(
    override val isLoading: Boolean = false,
    override val data: List<String> = listOf("all"),
    override val error: String = ""
) : State(isLoading, data, error)
