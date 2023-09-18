package com.parg3v.shoppingapp.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.ui.theme.ShimmingBackground
import com.parg3v.shoppingapp.ui.theme.ShimmingForeground

@Composable
fun Shimmer(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    loadingComposable: @Composable () -> Unit
) {
    if (isLoading) {
        loadingComposable()
    } else {
        contentAfterLoading()
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = stringResource(id = R.string.text_loading))
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = stringResource(id = R.string.text_loading)
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                ShimmingBackground,
                ShimmingForeground,
                ShimmingBackground
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}


@Composable
fun RowPlaceHolder(
    labelsRowModifier: Modifier = Modifier,
    productsRowModifier: Modifier = Modifier,
    item: @Composable (item: Any) -> Unit,
    itemsCount: Int = integerResource(id = R.integer.card_big_list_range)
) {
    Row(
        modifier = labelsRowModifier
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .weight(1F)
                .height(30.dp)
                .width(150.dp)
                .padding(start = 10.dp)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .weight(1F)
                .height(30.dp)
                .width(100.dp)
                .shimmerEffect()
        )
    }

    LazyRow(
        modifier = productsRowModifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.card_space_between)
        )
    ) {
        items(itemsCount) { item ->
            item(item)
        }
    }
}

@Composable
fun ShoppingItemPlaceholder() {
    val cornerRadius = integerResource(id = R.integer.card_border_radius)
    Card(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.card_width))
            .clip(RoundedCornerShape(cornerRadius)),
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
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_image_border_radius)))
                    .fillMaxWidth(0.8F)
                    .fillMaxHeight(0.5F)
                    .align(Alignment.CenterHorizontally)
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.card_space_between)))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(15.dp)
                        .align(Alignment.CenterVertically)
                        .shimmerEffect()
                )
                FloatingActionButton(
                    onClick = {},
                    containerColor = Color.Gray
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
fun Preview() {
    RowPlaceHolder(item = { ShoppingItemPlaceholder() })
}