package com.parg3v.shoppingapp.view.grid

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.parg3v.domain.model.Product
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.components.ShoppingItem
import com.parg3v.shoppingapp.model.ProductsListState
import com.parg3v.shoppingapp.navigation.Screen

@Composable
fun GridScreen(listDefinition: String, navController: NavController) {
    Log.d("TAG", "GridScreen: $listDefinition")
    val list = ProductsListState.withDummy(20).data
    GridScreenUI(items = list, navController = navController)
}

@Composable
fun GridScreenUI(items: List<Product>, navController: NavController) {
    LazyVerticalGrid(
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
        }
    )
}

@Preview
@Composable
fun Preview() {
    GridScreenUI(
        items = ProductsListState.withDummy(20).data,
        navController = rememberNavController()
    )
}