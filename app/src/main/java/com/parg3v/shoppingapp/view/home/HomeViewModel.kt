package com.parg3v.shoppingapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.use_cases.GetAllProductsUseCase
import com.parg3v.domain.use_cases.GetProductsByCategoryUseCase
import com.parg3v.shoppingapp.model.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase
) : ViewModel() {

    private val _productsState = MutableStateFlow(ProductListState())
    val productsState: StateFlow<ProductListState> = _productsState

    private val _productsByCategoryState = MutableStateFlow(ProductListState())
    val productsByCategoryState: StateFlow<ProductListState> = _productsByCategoryState

    init {
        getProducts()
    }

    fun getByCategory(value: String){
        getProductsByCategory(value)
    }

    private fun getProducts() {
        getAllProductsUseCase().onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _productsState.value = ProductListState(products = result.data ?: emptyList())
                }

                is ResultOf.Failure -> {
                    _productsState.value =
                        ProductListState(error = result.message ?: "An unexpected error occurred")
                }

                is ResultOf.Loading -> {
                    _productsState.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getProductsByCategory(category: String) {
        getProductsByCategoryUseCase(category).onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _productsByCategoryState.value =
                        ProductListState(products = result.data ?: emptyList())
                }

                is ResultOf.Failure -> {
                    _productsByCategoryState.value =
                        ProductListState(error = result.message ?: "An unexpected error occurred")
                }

                is ResultOf.Loading -> {
                    _productsByCategoryState.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}