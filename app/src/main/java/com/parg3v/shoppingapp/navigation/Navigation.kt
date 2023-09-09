package com.parg3v.shoppingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.parg3v.shoppingapp.screen.CartScreen
import com.parg3v.shoppingapp.screen.ExploreScreen
import com.parg3v.shoppingapp.screen.FavouriteScreen
import com.parg3v.shoppingapp.screen.HomeScreen

@Composable
fun Navigation(navController:NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
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