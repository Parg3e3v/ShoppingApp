package com.parg3v.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.parg3v.domain.model.Product
import com.parg3v.shoppingapp.R

@Composable
fun ShoppingItemRow(itemList: List<Product>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.card_space_between)
        )
    ) {
        items(itemList) { item ->
            ShoppingItem(item = item, onButtonClick = { /*TODO*/ }) {
                /*TODO*/
            }
        }
    }
}