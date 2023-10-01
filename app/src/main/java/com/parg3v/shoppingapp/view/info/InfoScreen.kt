@file:OptIn(ExperimentalMaterial3Api::class)

package com.parg3v.shoppingapp.view.info

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.with
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.parg3v.domain.model.DummyProduct
import com.parg3v.domain.model.Product
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.navigation.Screen
import com.parg3v.shoppingapp.ui.theme.CustomPurple
import com.parg3v.shoppingapp.ui.theme.ProductInfoText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun InfoScreen(item: Product, navController: NavController, scaffoldState: BottomSheetScaffoldState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ProductDetailsUI(
            item = item,
            navController = navController,
            scaffoldState = scaffoldState
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductDetailsUI(
    item: Product,
    navController: NavController,
    scaffoldState: BottomSheetScaffoldState
) {
    val scope = rememberCoroutineScope()
    val placeholder = painterResource(id = R.drawable.placeholder)
    var count by remember { mutableStateOf(0) }
    var expendState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expendState) 180F else 0F, label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.product_details_screen_padding))
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.product_details_space_between)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(item.image).crossfade(true)
                    .build(),
                error = placeholder,
                placeholder = placeholder,
                contentDescription = item.title,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_image_border_radius)))
                    .fillMaxWidth()
                    .fillMaxHeight(0.4F)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title, style = ProductInfoText, modifier = Modifier.weight(6F)
                )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(1F)) {
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
                IconButton(onClick = { if (count > 0) count-- }, enabled = count > 0) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_subtract),
                        tint = if (count > 0) CustomPurple else Color.Gray,
                        contentDescription = stringResource(id = R.string.subtract)
                    )
                }

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
                            text = targetCount.toString(),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                IconButton(onClick = { count++ }) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Filled.Add),
                        tint = CustomPurple,
                        contentDescription = stringResource(id = R.string.add)
                    )
                }

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

            Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.product_details_description_padding))) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.reviews))
                Text(text = "${item.rating.rate}☆ with ${item.rating.count} reviews")

            }
        }
        Button(
            onClick = {
                addToCart(
                    navController = navController,
                    scope = scope,
                    scaffoldState = scaffoldState
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.product_details_add_to_cart_button_height))
                .align(Alignment.BottomCenter)
        ) {
            Text(text = stringResource(id = R.string.add_to_cart))
        }
    }
}

fun addToCart(
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: BottomSheetScaffoldState
) {
    navController.navigate(Screen.HomeScreen.route)
    scope.launch { scaffoldState.snackbarHostState.showSnackbar(message = "Added to Cart") }
}

@Preview
@Composable
fun Preview() {
    InfoScreen(
        item = DummyProduct,
        navController = rememberNavController(),
        scaffoldState = rememberBottomSheetScaffoldState()
    )
}