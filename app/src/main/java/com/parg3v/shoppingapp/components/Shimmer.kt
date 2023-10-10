package com.parg3v.shoppingapp.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.ui.theme.CustomPurple
import com.parg3v.shoppingapp.ui.theme.ShimmingBackground
import com.parg3v.shoppingapp.ui.theme.ShimmingForeground
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.FillShimmer
import com.smarttoolfactory.ratingbar.model.GestureStrategy
import com.smarttoolfactory.ratingbar.model.ShimmerEffect

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
fun ErrorComposable(vararg errors: String) {
    val text = buildString {
        append(stringResource(id = R.string.error_text))
        errors.forEach { arg ->
            append("\n\n$arg")
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun RowPlaceHolder(
    modifier: Modifier = Modifier,
    item: @Composable (item: Any) -> Unit,
    itemsCount: Int = integerResource(id = R.integer.card_big_list_range)
) {
    Row(
        modifier = Modifier
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
        modifier = modifier.fillMaxWidth(),
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

@Composable
fun InfoScreenPlaceholder() {
    val shimColors = remember {
        listOf(
            Color.Gray.copy(alpha = .9f),
            Color.Gray.copy(alpha = .6f),
            Color.Gray.copy(alpha = .9f),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.product_details_screen_padding)),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .weight(1F),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.product_details_space_between)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .shimmerEffect()
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_image_border_radius)))
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.product_details_image_height))
            )

            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(6F)
                        .height(30.dp)
                        .fillMaxWidth(0.3F)
                        .shimmerEffect(),
                )

                Spacer(modifier = Modifier.weight(6F))

                IconButton(
                    onClick = { },
                    modifier = Modifier.weight(1F)
                ) {
                    Icon(
                        painter = rememberVectorPainter(Icons.Outlined.FavoriteBorder),
                        contentDescription = stringResource(id = R.string.add_to_favourites)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.product_details_count_buttons_padding))
                        .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.icon_subtract),
                    tint = Color.Gray,
                    contentDescription = stringResource(id = R.string.subtract)
                )

                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, Color.Gray),
                            RoundedCornerShape(dimensionResource(id = R.dimen.product_count_corner_radius))
                        )
                        .padding(
                            vertical = dimensionResource(id = R.dimen.product_count_padding_vertical),
                            horizontal = dimensionResource(id = R.dimen.product_count_padding_horizontal)
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        text = "1",
                        textAlign = TextAlign.Center
                    )

                }

                Icon(
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.product_details_count_buttons_padding))
                        .fillMaxHeight(),
                    painter = rememberVectorPainter(image = Icons.Filled.Add),
                    tint = CustomPurple,
                    contentDescription = stringResource(id = R.string.add)
                )

                Spacer(modifier = Modifier.weight(1F))

                Box(
                    modifier = Modifier
                        .weight(1F)
                        .height(30.dp)
                        .width(50.dp)
                        .shimmerEffect())
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .height(1.dp)
                    .background(Color.Gray)
            )

            Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.product_info_description_padding))) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = R.string.product_details))
                        IconButton(
                            onClick = { },
                        ) {
                            Icon(
                                painter = rememberVectorPainter(image = Icons.Filled.KeyboardArrowDown),
                                contentDescription = stringResource(id = R.string.see_more)
                            )
                        }
                    }
                }

                Text(
                    text = stringResource(id = R.string.reviews),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.product_details_reviews_title_vertical_padding))
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .height(15.dp)
                            .width(40.dp)
                            .shimmerEffect()
                    )

                    Spacer(modifier = Modifier.weight(1F))

                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .padding(horizontal = dimensionResource(id = R.dimen.product_details_rate_text_padding))
                            .height(15.dp)
                            .width(20.dp)
                            .shimmerEffect()
                    )

                    RatingBar(
                        rating = 5F,
                        painterEmpty = painterResource(id = R.drawable.star_background),
                        painterFilled = painterResource(id = R.drawable.star_foreground),
                        tintEmpty = Color.Gray,
                        tintFilled = Color.Gray,
                        gestureStrategy = GestureStrategy.None,
                        itemSize = dimensionResource(id = R.dimen.product_details_ratingbar_size),
                        shimmerEffect = ShimmerEffect(
                            FillShimmer(
                                colors = shimColors, animationSpec = infiniteRepeatable(
                                    animation = tween(
                                        durationMillis = 1000,
                                        easing = LinearEasing
                                    ), repeatMode = RepeatMode.Restart
                                ), solidBorder = true
                            )
                        )
                    ) {}
                }
            }
        }

        Button(
            enabled = false,
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.product_details_add_to_cart_button_height))
        ) {
            Text(text = stringResource(id = R.string.add_to_cart))
        }
    }
}


@Preview
@Composable
fun Preview() {
    InfoScreenPlaceholder()
}