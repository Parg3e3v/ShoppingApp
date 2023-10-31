package com.parg3v.shoppingapp.model

import com.parg3v.shoppingapp.utils.UiText

open class State(
    open val isLoading: Boolean = false,
    open val data: Any = Any(),
    open val error: UiText = UiText.DynamicString("")
)
