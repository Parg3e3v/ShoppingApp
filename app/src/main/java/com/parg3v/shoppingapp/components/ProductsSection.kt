package com.parg3v.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.parg3v.shoppingapp.model.ProductListState
import com.parg3v.shoppingapp.ui.theme.CustomPurple


@Composable
fun ProductsSection(itemList: ProductListState, title: String, modifier: Modifier = Modifier) {
    val bigListSize = itemList.products.size > integerResource(id = R.integer.card_big_list_range)
    Box(modifier = modifier.height(dimensionResource(id = R.dimen.card_height) + 50.dp)) {
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
                    .weight(1F)
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
                    items(
                        if (bigListSize) itemList.products.subList(0, 5)
                        else itemList.products
                    ) { item ->
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