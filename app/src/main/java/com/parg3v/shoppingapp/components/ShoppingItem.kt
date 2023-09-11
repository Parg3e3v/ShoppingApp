package com.parg3v.shoppingapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.parg3v.domain.model.Product
import com.parg3v.domain.model.Rating
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.ui.theme.CustomPurple

@Composable
fun ShoppingItem(
    item: Product,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    onClick: () -> Unit
) {
    val placeholder = painterResource(id = R.drawable.placeholder)
    val cornerRadius = integerResource(id = R.integer.card_border_radius)
    Card(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.card_width))
            .clip(RoundedCornerShape(cornerRadius))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(dimensionResource(id = R.dimen.card_border_size), Color.Gray),
        shape = RoundedCornerShape(percent = cornerRadius)
    ) {
        Column(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.card_height))
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true)
                    .build(),
                error = placeholder,
                placeholder = placeholder,
                contentDescription = item.title,
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(0.6F)
            )

            Text(text = item.title)

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "${item.price}$",
                        textAlign = TextAlign.Center
                    )
                }
                FloatingActionButton(

                    onClick = onButtonClick,
                    containerColor = CustomPurple
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_to_cart),
                        tint = Color.White
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun CardPreview() {
    ShoppingItem(
        Product(
            0,
            "Title",
            50F,
            "50$",
            "50$",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(4.5F, 500)
        ),
        Modifier,
        onButtonClick = {
            // TODO
        }
    ) {
        // TODO
    }
}