package com.parg3v.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.parg3v.domain.model.Product

@Composable
fun ShoppingItemRow(itemList: List<Product>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(itemList) {item ->
            ShoppingItem(
                item = item,
                onButtonClick = {
                    // TODO
                }
            ){
                // TODO
            }
        }
    }
}