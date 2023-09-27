package com.parg3v.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.model.CategoriesListState
import com.parg3v.shoppingapp.model.ProductListState
import com.parg3v.shoppingapp.navigation.Screen
import com.parg3v.shoppingapp.ui.theme.CustomPurple


@Composable
fun ProductsSection(
    navController: NavController,
    modifier: Modifier = Modifier,
    itemList: ProductListState,
    title: String = "",
    categoriesListState: CategoriesListState = CategoriesListState(isLoading = false)
) {

    Column(modifier = modifier.wrapContentHeight()) {


        Shimmer(isLoading = itemList.isLoading || categoriesListState.isLoading,
            contentAfterLoading = {
                ProductsSectionUI(
                    navController = navController,
                    modifier = Modifier,
                    itemList = itemList,
                    title = title,
                    categoriesListState = categoriesListState
                )
            },
            loadingComposable = {
                RowPlaceHolder(
                    modifier = Modifier,
                    item = { ShoppingItemPlaceholder() }
                )
            })
    }
}

@Composable
fun ProductsSectionUI(
    navController: NavController,
    modifier: Modifier = Modifier,
    itemList: ProductListState,
    title: String,
    categoriesListState: CategoriesListState
) {
    val listRange = integerResource(id = R.integer.card_big_list_range)
    val bigListSize = itemList.products.size > listRange
    val labelText =
        if (categoriesListState.categories.isEmpty()) title else categoriesListState.categories[0]
            .replaceFirstChar { it.uppercase() }

    Row(
        modifier = Modifier.wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .weight(1F)
                .padding(start = dimensionResource(id = R.dimen.product_section_label_padding_start))
        )
        if (bigListSize) {
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .weight(1F)
            ) {
                Text(
                    text = stringResource(id = R.string.see_all_button),
                    style = MaterialTheme.typography.labelMedium,
                    color = CustomPurple
                )
            }
        }
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.card_space_between)
        ), modifier = modifier.fillMaxWidth()
    ) {
        items(
            if (bigListSize) itemList.products.subList(0, listRange)
            else itemList.products
        ) { item ->
            ShoppingItem(item = item, onButtonClick = {}) {
                navController.navigate(Screen.InfoScreen.withArgsFromProduct(item))
            }
        }
    }
}