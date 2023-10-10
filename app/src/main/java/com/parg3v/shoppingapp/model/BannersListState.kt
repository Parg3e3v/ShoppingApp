package com.parg3v.shoppingapp.model

data class BannersListState(
    override val isLoading: Boolean = false,
    override val data: List<String> = emptyList(),
    override val error: String = ""
) : State(isLoading, data, error)
