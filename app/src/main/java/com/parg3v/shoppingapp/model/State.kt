package com.parg3v.shoppingapp.model

open class State(
    open val isLoading: Boolean = false,
    open val data: Any = Any(),
    open val error: String = ""
)
