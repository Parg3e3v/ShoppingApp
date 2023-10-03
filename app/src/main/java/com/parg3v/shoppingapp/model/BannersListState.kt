package com.parg3v.shoppingapp.model

data class BannersListState(
    val isLoading: Boolean = false,
    val banners: List<String> = emptyList(),
    val error: String = ""
)
