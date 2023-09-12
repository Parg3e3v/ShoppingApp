package com.parg3v.shoppingapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.use_cases.GetAllProductsUseCase
import com.parg3v.shoppingapp.model.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductListState())
    val state: StateFlow<ProductListState> = _state
    init {
        getProducts()
    }

    private fun getProducts() {
        getAllProductsUseCase().onEach{ result ->
            when(result){
                is ResultOf.Success<*> -> {
                    _state.value = ProductListState(products = result.data ?: emptyList())
                }
                is ResultOf.Failure -> {
                    _state.value = ProductListState(error = result.message ?: "An unexpected error occurred")
                }
                is ResultOf.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}