package com.parg3v.shoppingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.parg3v.shoppingapp.screen.ExploreScreen
import com.parg3v.shoppingapp.screen.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.ExploreScreen.route){
            ExploreScreen(navController = navController)
        }
    }
}