package com.parg3v.shoppingapp.view.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.components.AutoSlidingCarousel
import com.parg3v.shoppingapp.components.ProductsSection
import com.parg3v.shoppingapp.model.ProductListState
import com.parg3v.shoppingapp.ui.theme.ShoppingAppTheme


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val itemsList by viewModel.highlyRatedProductsState.collectAsState()
    val itemsListByCategory by viewModel.productsState.collectAsState()
    val bannersList by viewModel.bannersState.collectAsState()

    HomeScreenUI(
        navController = navController,
        itemsList = itemsList,
        itemsListByCategory = itemsListByCategory,
        images = bannersList.banners
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    navController: NavController,
    itemsList: ProductListState,
    itemsListByCategory: ProductListState,
    images: List<String>?
) {
    ShoppingAppTheme {
        if (itemsList.error.isNotBlank() || itemsListByCategory.error.isNotBlank()) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.error_text),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            return@ShoppingAppTheme
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.products_list_vertical_space))
        ) {
            item {
                AutoSlidingCarousel(itemsCount = images?.size ?: 3, itemContent = { index ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(images?.get(index))
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(dimensionResource(id = R.dimen.card_height)),
                        placeholder = ColorPainter(Color.Gray)
                    )
                })
            }

            items(listOf(itemsList, itemsListByCategory)) { state ->
                ProductsSection(
                    navController = navController,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.card_padding)),
                    itemList = state.products,
                    isLoading = state.isLoading,
                    title = state.category
                )
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun Preview() {
    HomeScreenUI(
        navController = rememberNavController(),
        itemsList = ProductListState.withDummy(4),
        itemsListByCategory = ProductListState.withDummy(4),
        images = List(4) { _ -> "https://png.pngtree.com/png-clipart/20220419/original/pngtree-red-festive-jewelry-poster-png-image_7538385.png" }
    )
}
