package com.parg3v.shoppingapp.view.info

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.components.ErrorComposable
import com.parg3v.shoppingapp.components.InfoScreenPlaceholder
import com.parg3v.shoppingapp.components.Shimmer
import com.parg3v.shoppingapp.model.ProductState
import com.parg3v.shoppingapp.model.dummyProduct
import com.parg3v.shoppingapp.navigation.Screen
import com.parg3v.shoppingapp.ui.theme.CustomPurple
import com.parg3v.shoppingapp.ui.theme.ProductInfoText
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.FillShimmer
import com.smarttoolfactory.ratingbar.model.GestureStrategy
import com.smarttoolfactory.ratingbar.model.ShimmerEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun InfoScreen(
    viewModel: InfoViewModel = hiltViewModel(),
    productId: String, navController: NavController, snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.getProductById(productId)
    }

    val item by viewModel.productState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Shimmer(isLoading = item.isLoading,
            contentAfterLoading = {
                ProductDetailsUI(
                    state = item,
                    navController = navController,
                    snackbarHostState = snackbarHostState
                )
            },
            loadingComposable = {
                InfoScreenPlaceholder()
            }
        )

    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductDetailsUI(
    state: ProductState,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    ratingColor: Color = Color(0xFFF3603F)
) {
    if (state.error.asString().isNotBlank()) {
        ErrorComposable(state.error.asString())
        return
    }
    val scope = rememberCoroutineScope()
    val placeholder = painterResource(id = R.drawable.placeholder)
    var count by remember { mutableStateOf(1) }
    var expendState by remember { mutableStateOf(false) }
    val message = stringResource(id = R.string.added_to_cart)
    val rotationState by animateFloatAsState(
        targetValue = if (expendState) 180F else 0F, label = ""
    )

    val shimColors = remember {
        listOf(
            ratingColor.copy(alpha = .9f),
            ratingColor.copy(alpha = .6f),
            ratingColor.copy(alpha = .9f),
        )
    }


    val item = state.data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.product_details_screen_padding)),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1F),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.product_details_space_between)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_image_border_radius)))
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.product_details_image_height)),
                model = ImageRequest.Builder(LocalContext.current).data(item.image).crossfade(true)
                    .build(),
                error = placeholder,
                placeholder = placeholder,
                contentDescription = item.title,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )

            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title, style = ProductInfoText, modifier = Modifier.weight(6F)
                )
                IconButton(
                    onClick = { /*TODO: Add to favourites in local Root database*/ },
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    modifier = Modifier
                        .clickable { if (count > 1) count-- }
                        .padding(end = dimensionResource(id = R.dimen.product_details_count_buttons_padding))
                        .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.icon_subtract),
                    tint = if (count > 1) CustomPurple else Color.Gray,
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
                    AnimatedContent(
                        targetState = count, transitionSpec = {
                            slideIntoContainer(
                                towards = if (targetState > initialState) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Down,
                                animationSpec = tween(durationMillis = 500)
                            ) with ExitTransition.None
                        }, label = ""
                    ) { targetCount ->
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            text = "$targetCount",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Icon(
                    modifier = Modifier
                        .clickable { count++ }
                        .padding(start = dimensionResource(id = R.dimen.product_details_count_buttons_padding))
                        .fillMaxHeight(),
                    painter = rememberVectorPainter(image = Icons.Filled.Add),
                    tint = CustomPurple,
                    contentDescription = stringResource(id = R.string.add)
                )

                Text(
                    modifier = Modifier.weight(1F),
                    text = "$${item.price}",
                    textAlign = TextAlign.End,
                    style = ProductInfoText
                )
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
                            onClick = { expendState = !expendState },
                            modifier = Modifier.rotate(rotationState)
                        ) {
                            Icon(
                                painter = rememberVectorPainter(image = Icons.Filled.KeyboardArrowDown),
                                contentDescription = stringResource(id = R.string.see_more)
                            )
                        }
                    }

                    AnimatedVisibility(
                        visible = expendState, enter = expandVertically() + fadeIn(
                            initialAlpha = 0.3f
                        ), exit = shrinkVertically() + fadeOut()
                    ) {
                        Text(
                            text = item.description, modifier = Modifier.fillMaxWidth()
                        )
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
                    Text(text = "${item.rating.count} " +
                            stringResource(id = R.string.review).replaceFirstChar { it.lowercase() }
                    )

                    Text(
                        modifier = Modifier
                            .weight(1F)
                            .padding(horizontal = dimensionResource(id = R.dimen.product_details_rate_text_padding)),
                        text = "${item.rating.rate}",
                        textAlign = TextAlign.End,
                    )

                    RatingBar(
                        rating = item.rating.rate,
                        painterEmpty = painterResource(id = R.drawable.star_background),
                        painterFilled = painterResource(id = R.drawable.star_foreground),
                        gestureStrategy = GestureStrategy.None,
                        itemSize = dimensionResource(id = R.dimen.product_details_ratingbar_size),
                        shimmerEffect = ShimmerEffect(
                            FillShimmer(
                                colors = shimColors, animationSpec = infiniteRepeatable(
                                    animation = tween(
                                        durationMillis = integerResource(id = R.integer.rating_shimm_duration_milies),
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
            onClick = {
                addToCart(
                    navController = navController,
                    scope = scope,
                    snackbarHostState = snackbarHostState,
                    message = message
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.product_details_add_to_cart_button_height))
        ) {
            Text(text = stringResource(id = R.string.add_to_cart))
        }
    }
}

fun addToCart(
    navController: NavController,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    message: String
) {
    // TODO: Add to cart in local Room database
    scope.launch {
        snackbarHostState.showSnackbar(message = message, duration = SnackbarDuration.Long)
    }
    navController.navigate(Screen.HomeScreen.route)
}

@Preview
@Composable
fun Preview() {
    ProductDetailsUI(
        state = ProductState(data = dummyProduct()),
        navController = rememberNavController(),
        snackbarHostState = SnackbarHostState()
    )
}