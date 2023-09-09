package com.parg3v.shoppingapp.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.parg3v.shoppingapp.model.BottomNavItem

@Composable
fun CustomScaffold(
    navController: NavController,
    showBottomBar: Boolean = true,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.Home,
        BottomNavItem.Explore,
        BottomNavItem.Cart,
        BottomNavItem.Favourite
    ),
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(
                    items = items,
                    navController = navController,
                    modifier = Modifier,
                    onItemClick = {
                        navController.navigate(it.route)
                    }
                )
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}