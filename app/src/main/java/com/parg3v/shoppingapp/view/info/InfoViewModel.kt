package com.parg3v.shoppingapp.view.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parg3v.domain.common.ResultOf
import com.parg3v.domain.use_cases.GetProductByIdUseCase
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.model.ProductState
import com.parg3v.shoppingapp.model.emptyProduct
import com.parg3v.shoppingapp.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {
    private val _productState = MutableStateFlow(ProductState())
    val productState: StateFlow<ProductState> = _productState

    fun getProductById(id: String) {
        getProductByIdUseCase(id).onEach { result ->
            when (result) {
                is ResultOf.Failure -> {
                    _productState.value =
                        ProductState(
                            error = result.message?.let { UiText.DynamicString(it) } ?: run {
                                UiText.StringResource(R.string.error_basic)
                            })
                }

                is ResultOf.Loading -> {
                    _productState.value = ProductState(isLoading = true)
                }

                is ResultOf.Success<*> -> {
                    _productState.value =
                        ProductState(data = result.data ?: emptyProduct())
                }
            }
        }.launchIn(viewModelScope)
    }
}