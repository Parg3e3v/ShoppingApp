package com.parg3v.shoppingapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.use_cases.GetAllCategoriesUseCase
import com.parg3v.domain.use_cases.GetBannersUseCase
import com.parg3v.domain.use_cases.GetHighlyRatedProductsUseCase
import com.parg3v.domain.use_cases.GetProductsByCategoryUseCase
import com.parg3v.shoppingapp.model.BannersListState
import com.parg3v.shoppingapp.model.CategoriesListState
import com.parg3v.shoppingapp.model.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getHighlyRatedProductsUseCase: GetHighlyRatedProductsUseCase,
    private val getBannersUseCase: GetBannersUseCase
) : ViewModel() {

    private val _allProductsState = MutableStateFlow(ProductListState())
    val allProductsState: StateFlow<ProductListState> = _allProductsState

    private val _productsByCategoryState = MutableStateFlow(ProductListState())
    val productsByCategoryState: StateFlow<ProductListState> = _productsByCategoryState

    private val _categoriesState = MutableStateFlow(CategoriesListState())
    val categoriesState: StateFlow<CategoriesListState> = _categoriesState

    private val _highlyRatedProductsState = MutableStateFlow(ProductListState())
    val highlyRatedProductsState: StateFlow<ProductListState> = _highlyRatedProductsState

    private val _bannersState = MutableStateFlow(BannersListState())
    val bannersState: StateFlow<BannersListState> = _bannersState

    init {
        getProducts()
        getBanners()
        getHighlyRatedProducts()
        getAllCategories()
    }

    private fun getProducts() {
        getProductsByCategoryUseCase("all").onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _allProductsState.value = ProductListState(products = result.data ?: emptyList())
                }

                is ResultOf.Failure -> {
                    _allProductsState.value =
                        ProductListState(error = result.message ?: "An unexpected error occurred")
                }

                is ResultOf.Loading -> {
                    _allProductsState.value = ProductListState(isLoading = true)
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

    private fun getAllCategories() {
        getAllCategoriesUseCase().onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _categoriesState.value = CategoriesListState(categories = result.data.orEmpty())
                    if (result.data!!.isNotEmpty())
                        getProductsByCategory(categoriesState.value.categories[0])
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
                        ProductListState(products = result.data ?: emptyList())
                }

                is ResultOf.Failure -> {
                    _highlyRatedProductsState.value =
                        ProductListState(error = result.message ?: "An unexpected error occurred")
                }

                is ResultOf.Loading -> {
                    _highlyRatedProductsState.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getBanners() {
        getBannersUseCase().onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _bannersState.value = BannersListState(banners = result.data ?: emptyList())
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