package com.parg3v.shoppingapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.parg3v.domain.model.Product
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.model.dummyProduct
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
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_image_border_radius)))
                    .fillMaxWidth(0.8F)
                    .fillMaxHeight(0.5F)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.card_space_image_title)))

            Text(
                text = item.title,
                maxLines = integerResource(id = R.integer.card_title_max_line),
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${item.price}$",
                    textAlign = TextAlign.Center
                )
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
        dummyProduct(),
        Modifier,
        onButtonClick = {}
    ) {}
}