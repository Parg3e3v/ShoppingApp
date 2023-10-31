package com.parg3v.shoppingapp.view.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.components.ErrorComposable
import com.parg3v.shoppingapp.components.ShoppingItem
import com.parg3v.shoppingapp.model.ProductsListState
import com.parg3v.shoppingapp.navigation.Screen
import com.parg3v.shoppingapp.utils.UiText

@Composable
fun GridScreen(
    category: String, navController: NavController, viewModel: GridViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getProducts(category = UiText.DynamicString(category), context = context)
    }

    val listState by viewModel.productsState.collectAsStateWithLifecycle()

    GridScreenUI(productsListState = listState, navController = navController)
}

@Composable
fun GridScreenUI(productsListState: ProductsListState, navController: NavController) {
    if (productsListState.error.asString().isNotBlank()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            ErrorComposable(productsListState.error.asString())
        }
        return
    }
    val items = productsListState.data
    LazyVerticalGrid(modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.card_width)),
        content = {
            items(items = items) { item ->
                ShoppingItem(
                    item = item, onButtonClick = {}, modifier = Modifier.padding(
                        dimensionResource(id = R.dimen.product_grid_padding)
                    )
                ) {
                    navController.navigate(Screen.InfoScreen.withArgs(item.id.toString()))
                }
            }
        })
}

@Preview
@Composable
fun Preview() {
    GridScreenUI(
        productsListState = ProductsListState.withDummy(20), navController = rememberNavController()
    )
}