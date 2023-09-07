package com.parg3v.shoppingapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingItem(
    painter: Painter,
    title: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(170.dp)
            .border(2.dp, Color.Gray)
    ) {
        Column(modifier = Modifier
            .height(250.dp)
            .padding(15.dp)) {
            Image(painter = painter, contentDescription = title)
            Text(text = title)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = price)
        }
    }
}