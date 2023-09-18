package com.parg3v.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.model.CategoriesListState
import com.parg3v.shoppingapp.model.ProductListState
import com.parg3v.shoppingapp.ui.theme.CustomPurple


@Composable
fun ProductsSection(
    modifier: Modifier = Modifier,
    itemList: ProductListState,
    title: String = "",
    categoriesListState: CategoriesListState = CategoriesListState(isLoading = false)
) {


    Box(modifier = modifier.height(dimensionResource(id = R.dimen.card_height) + 50.dp)) {

        Shimmer(isLoading = itemList.isLoading || categoriesListState.isLoading,
            contentAfterLoading = {
                ProductsSectionUI(
                    labelsRowModifier = Modifier.align(Alignment.TopStart),
                    productsRowModifier = Modifier.align(Alignment.BottomStart),
                    itemList = itemList,
                    title = title,
                    categoriesListState = categoriesListState
                )
            },
            loadingComposable = {
                RowPlaceHolder(
                    labelsRowModifier = Modifier.align(Alignment.TopStart),
                    productsRowModifier = Modifier.align(Alignment.BottomStart),
                    item = { ShoppingItemPlaceholder() })
            })
    }
}

@Composable
fun ProductsSectionUI(
    labelsRowModifier: Modifier = Modifier,
    productsRowModifier: Modifier = Modifier,
    itemList: ProductListState,
    title: String,
    categoriesListState: CategoriesListState
) {
    val listRange = integerResource(id = R.integer.card_big_list_range)
    val bigListSize = itemList.products.size > listRange
    val labelText =
        if (categoriesListState.categories.isEmpty()) title else categoriesListState.categories[0].replaceFirstChar { it.uppercase() }

    Row(
        modifier = labelsRowModifier
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .weight(1F)
                .padding(start = 10.dp)
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
        ), modifier = productsRowModifier
            .fillMaxWidth()
    ) {
        items(
            if (bigListSize) itemList.products.subList(0, listRange)
            else itemList.products
        ) { item ->
            ShoppingItem(item = item, onButtonClick = { /*TODO*/ }) {
                /*TODO*/
            }
        }
    }
}