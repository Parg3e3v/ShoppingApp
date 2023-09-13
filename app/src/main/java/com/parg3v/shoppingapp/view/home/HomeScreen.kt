package com.parg3v.shoppingapp.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.components.ProductsSection
import com.parg3v.shoppingapp.ui.theme.ShoppingAppTheme


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val itemsList by viewModel.productsState.collectAsState()
    val itemsListByCategory by viewModel.productsByCategoryState.collectAsState()

    ShoppingAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement
                .spacedBy(dimensionResource(id = R.dimen.products_list_vertical_space))
        ) {

            LaunchedEffect(Unit) {
                viewModel.getByCategory("electronics")
            }

            ProductsSection(
                itemList = itemsList,
                title = "Best Celling",
                modifier = Modifier.padding(dimensionResource(id = R.dimen.card_padding))
            )

            ProductsSection(
                itemList = itemsListByCategory,
                title = "Electronics",
                modifier = Modifier.padding(dimensionResource(id = R.dimen.card_padding))
            )

            if (itemsList.error.isNotBlank() || itemsListByCategory.error.isNotBlank()) {
                Text(
                    text = stringResource(id = R.string.error_text),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

