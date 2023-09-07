package com.parg3v.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.parg3v.shoppingapp.components.ShoppingItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyVerticalGrid(columns = GridCells.Adaptive(170.dp)) {
                items(5) {
                    for (i in 1..5) {
                        ShoppingItem(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            title = "title",
                            price = "50.5"
                        )
                    }
                }
            }
        }
        viewModel.state.observe(this) {
            println("[LOG] loading data.....")
            if (!it.isLoading) {
                if (it.error.isNotBlank())
                    println(it.error)
                else {
                    println("[LOG] Success! your data is -> ${it.products}")
                }
            }
        }
    }
}

