package com.parg3v.shoppingapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.use_cases.GetAllCategoriesUseCase
import com.parg3v.domain.use_cases.GetBannersUseCase
import com.parg3v.domain.use_cases.GetHighlyRatedProductsUseCase
import com.parg3v.domain.use_cases.GetProductsUseCase
import com.parg3v.shoppingapp.model.BannersListState
import com.parg3v.shoppingapp.model.CategoriesListState
import com.parg3v.shoppingapp.model.ProductsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getHighlyRatedProductsUseCase: GetHighlyRatedProductsUseCase,
    private val getBannersUseCase: GetBannersUseCase
) : ViewModel() {

    private val _productsState = MutableStateFlow(ProductsListState())
    val productsState: StateFlow<ProductsListState> = _productsState

    private val _categoriesState = MutableStateFlow(CategoriesListState())
    val categoriesState: StateFlow<CategoriesListState> = _categoriesState

    private val _highlyRatedProductsState = MutableStateFlow(ProductsListState())
    val highlyRatedProductsState: StateFlow<ProductsListState> = _highlyRatedProductsState

    private val _bannersState = MutableStateFlow(BannersListState())
    val bannersState: StateFlow<BannersListState> = _bannersState

    init {
        getBanners()
        getAllCategories()
        getHighlyRatedProducts()
    }

    private fun getProductsByCategory(category: String) {
        getProductsUseCase(category).onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _productsState.value =
                        ProductsListState(data = result.data.orEmpty(), category = category)
                }

                is ResultOf.Failure -> {
                    _productsState.value =
                        ProductsListState(
                            error = result.message ?: "An unexpected error occurred",
                            category = category
                        )
                }

                is ResultOf.Loading -> {
                    _productsState.value =
                        ProductsListState(isLoading = true, category = category)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllCategories() {
        getAllCategoriesUseCase().onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _categoriesState.value =
                        CategoriesListState(data = result.data ?: listOf("all"))
                    getProductsByCategory(category = categoriesState.value.data.random())
                }

                is ResultOf.Failure -> {
                    _categoriesState.value =
                        CategoriesListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                }

                is ResultOf.Loading -> {
                    _categoriesState.value = CategoriesListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getHighlyRatedProducts() {
        getHighlyRatedProductsUseCase().onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _highlyRatedProductsState.value =
                        ProductsListState(
                            data = result.data.orEmpty(),
                            /* TODO */
                            category = "Best Celling"
                        )
                }

                is ResultOf.Failure -> {
                    _highlyRatedProductsState.value =
                        ProductsListState(error = result.message ?: "An unexpected error occurred")
                }

                is ResultOf.Loading -> {
                    _highlyRatedProductsState.value = ProductsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBanners() {
        getBannersUseCase().onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _bannersState.value = BannersListState(data = result.data.orEmpty())
                }

                is ResultOf.Failure -> {
                    _bannersState.value =
                        BannersListState(error = result.message ?: "An unexpected error occurred")
                }

                is ResultOf.Loading -> {
                    _bannersState.value = BannersListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}