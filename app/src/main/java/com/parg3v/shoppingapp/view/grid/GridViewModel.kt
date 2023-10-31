package com.parg3v.shoppingapp.view.grid

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.use_cases.GetHighlyRatedProductsUseCase
import com.parg3v.domain.use_cases.GetProductsUseCase
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.model.ProductsListState
import com.parg3v.shoppingapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getHighlyRatedProductsUseCase: GetHighlyRatedProductsUseCase
) : ViewModel() {
    private val _productsState = MutableStateFlow(ProductsListState())
    val productsState: StateFlow<ProductsListState> = _productsState

    fun getProducts(category: UiText, context: Context) {
        val bestSelling = UiText.StringResource(R.string.best_selling)
        when (category) {
            bestSelling -> getHighlyRatedProducts()
            else -> getProductsByCategory(category.asString(context))
        }
    }

    private fun getProductsByCategory(category: String) {
        val categoryUiText = UiText.DynamicString(category)
        getProductsUseCase(category).onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _productsState.value =
                        ProductsListState(data = result.data.orEmpty(), category = categoryUiText)
                }

                is ResultOf.Failure -> {
                    _productsState.value =
                        ProductsListState(
                            error = result.message?.let { UiText.DynamicString(it) } ?: run {
                                UiText.StringResource(R.string.error_basic)
                            },
                            category = categoryUiText
                        )
                }

                is ResultOf.Loading -> {
                    _productsState.value =
                        ProductsListState(isLoading = true, category = categoryUiText)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getHighlyRatedProducts() {
        getHighlyRatedProductsUseCase().onEach { result ->
            when (result) {
                is ResultOf.Success<*> -> {
                    _productsState.value =
                        ProductsListState(
                            data = result.data.orEmpty(),
                            category = UiText.StringResource(R.string.best_selling)
                        )
                }

                is ResultOf.Failure -> {
                    _productsState.value =
                        ProductsListState(error = result.message?.let { UiText.DynamicString(it) }
                            ?: run {
                                UiText.StringResource(R.string.error_basic)
                            })
                }

                is ResultOf.Loading -> {
                    _productsState.value = ProductsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}