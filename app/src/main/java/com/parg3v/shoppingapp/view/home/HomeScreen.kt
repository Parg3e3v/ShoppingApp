package com.parg3v.shoppingapp.view.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.parg3v.domain.model.Product
import com.parg3v.domain.model.Rating
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.components.AutoSlidingCarousel
import com.parg3v.shoppingapp.components.ProductsSection
import com.parg3v.shoppingapp.model.CategoriesListState
import com.parg3v.shoppingapp.model.ProductListState
import com.parg3v.shoppingapp.ui.theme.ShoppingAppTheme


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val itemsList by viewModel.highlyRatedProductsState.collectAsState()
    val categories by viewModel.categoriesState.collectAsState()
    val itemsListByCategory by viewModel.productsByCategoryState.collectAsState()
    val bannersList by viewModel.bannersState.collectAsState()

    HomeScreenUI(
        navController = navController,
        itemsList = itemsList,
        itemsListByCategory = itemsListByCategory,
        categories = categories,
        images = bannersList.banners
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    navController: NavController,
    itemsList: ProductListState,
    itemsListByCategory: ProductListState,
    categories: CategoriesListState,
    images: List<String>?
) {
    ShoppingAppTheme {
        if (itemsList.error.isNotBlank() || itemsListByCategory.error.isNotBlank()) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.error_text),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            return@ShoppingAppTheme
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.products_list_vertical_space))
        ) {
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

            ProductsSection(
                navController = navController,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.card_padding)),
                itemList = itemsList,
                title = "Best Celling"
            )

            ProductsSection(
                navController = navController,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.card_padding)),
                itemList = itemsListByCategory,
                categoriesListState = categories
            )

        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun Preview() {
    val sampleProduct = Product(
        1,
        "Title",
        44.4F,
        "description",
        "electronics",
        "https://freesvg.org/img/blueteeshirt.png",
        Rating(4.5F, 57)
    )
    HomeScreenUI(
        navController = rememberNavController(), itemsList = ProductListState(
            products = listOf(
                sampleProduct, sampleProduct, sampleProduct, sampleProduct
            )
        ), itemsListByCategory = ProductListState(
            products = listOf(
                sampleProduct, sampleProduct, sampleProduct, sampleProduct
            )
        ), categories = CategoriesListState(categories = listOf("Jewelery")), images = listOf(
            "https://png.pngtree.com/png-clipart/20220419/original/pngtree-red-festive-jewelry-poster-png-image_7538385.png",
            "https://png.pngtree.com/png-clipart/20220419/original/pngtree-red-festive-jewelry-poster-png-image_7538385.png",
            "https://png.pngtree.com/png-clipart/20220419/original/pngtree-red-festive-jewelry-poster-png-image_7538385.png",
            "https://png.pngtree.com/png-clipart/20220419/original/pngtree-red-festive-jewelry-poster-png-image_7538385.png",
        )
    )
}
