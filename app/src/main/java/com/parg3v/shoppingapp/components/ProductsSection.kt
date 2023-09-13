package com.parg3v.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.model.ProductListState
import com.parg3v.shoppingapp.ui.theme.CustomPurple


@Composable
fun ProductsSection(itemList: ProductListState, title: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.height(dimensionResource(id = R.dimen.card_height) + 50.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.TopStart)
        )
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Text(
                text = stringResource(id = R.string.see_all_button),
                style = MaterialTheme.typography.labelMedium,
                color = CustomPurple
            )
        }
        Shimmer(
            isLoading = itemList.isLoading,
            contentAfterLoading = {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.card_space_between)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ) {
                    items(itemList.products) { item ->
                        ShoppingItem(item = item, onButtonClick = { /*TODO*/ }) {
                            /*TODO*/
                        }
                    }
                }
            },
            loadingComposable = {
                RowPlaceHolder(
                    modifier = Modifier.align(Alignment.BottomStart),
                    item = { ShoppingItemPlaceholder() })
            }
        )
    }
}