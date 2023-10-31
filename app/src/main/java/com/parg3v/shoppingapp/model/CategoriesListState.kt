package com.parg3v.shoppingapp.model

import com.parg3v.shoppingapp.utils.UiText

data class CategoriesListState(
    override val isLoading: Boolean = false,
    override val data: List<String> = listOf("all"),
    override val error: UiText = UiText.DynamicString("")
) : State(isLoading, data, error)
