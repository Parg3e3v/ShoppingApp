package com.parg3v.shoppingapp.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.components.RowPlaceHolder
import com.parg3v.shoppingapp.components.ShimmerItem
import com.parg3v.shoppingapp.components.ShoppingItemPlaceholder
import com.parg3v.shoppingapp.components.ShoppingItemRow
import com.parg3v.shoppingapp.ui.theme.ShoppingAppTheme


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val itemsList by viewModel.state.collectAsState()

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
                ShimmerItem(
                    isLoading = itemsList.isLoading,
                    contentAfterLoading = { ShoppingItemRow(itemsList.products) },
                    loadingComposable = { RowPlaceHolder(item = { ShoppingItemPlaceholder() }) }
                )
            }

            if (itemsList.error.isNotBlank()) {
                Text(
                    text = stringResource(id = R.string.error_text),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}