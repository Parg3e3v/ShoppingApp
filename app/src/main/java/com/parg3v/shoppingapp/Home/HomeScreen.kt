package com.parg3v.shoppingapp.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.parg3v.domain.model.Product
import com.parg3v.domain.model.Rating
import com.parg3v.shoppingapp.components.ShoppingItemRow
import com.parg3v.shoppingapp.ui.theme.ShoppingAppTheme


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

//    val itemsList by viewModel.state.collectAsState(initial = false)

    val itemsList = listOf(
        Product(
            0,
            "title",
            50F,
            "desc",
            "cat",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(5F, 500)
        ),
        Product(
            0,
            "title",
            50F,
            "desc",
            "cat",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(5F, 500)
        ),
        Product(
            0,
            "title",
            50F,
            "desc",
            "cat",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(5F, 500)
        ),
        Product(
            0,
            "title",
            50F,
            "desc",
            "cat",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(5F, 500)
        ),
        Product(
            0,
            "title",
            50F,
            "desc",
            "cat",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            Rating(5F, 500)
        )
    )

    ShoppingAppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShoppingItemRow(itemsList)
                // TODO
            }
        }

    }
}