package com.parg3v.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.parg3v.shoppingapp.R

@Composable
fun ShoppingItemRow() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(5) {
            ShoppingItem(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                title = "title $it",
                price = "50.5"
            )
        }
    }
}