package com.parg3v.shoppingapp.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.use_cases.GetAllProductsUseCase
import com.parg3v.shoppingapp.model.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<ProductListState>()
    val state: SharedFlow<ProductListState> = _state
    init {
        getProducts()
    }

    private fun getProducts() {
        getAllProductsUseCase().onEach{ result ->
            when(result){
                is ResultOf.Success<*> -> {
                    _state.emit(ProductListState(products = result.data ?: emptyList()))
                }
                is ResultOf.Loading -> {
                    _state.emit(ProductListState(error = result.message ?: "An unexpected error occurred"))
                }
                is ResultOf.Failure -> {
                    _state.emit(ProductListState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }
}