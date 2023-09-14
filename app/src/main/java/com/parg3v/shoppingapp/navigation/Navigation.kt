package com.parg3v.shoppingapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.parg3v.shoppingapp.view.cart.CartScreen
import com.parg3v.shoppingapp.view.explore.ExploreScreen
import com.parg3v.shoppingapp.view.favourite.FavouriteScreen
import com.parg3v.shoppingapp.view.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.HomeScreen.route){
            HomeScreen()
        }
        composable(Screen.ExploreScreen.route){
            ExploreScreen()
        }
        composable(Screen.CartScreen.route){
            CartScreen()
        }
        composable(Screen.FavouriteScreen.route){
            FavouriteScreen()
        }
    }
}