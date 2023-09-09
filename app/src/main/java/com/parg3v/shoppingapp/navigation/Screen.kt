package com.parg3v.shoppingapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object ExploreScreen: Screen("explore_screen")
    object CartScreen: Screen("cart_screen")
    object FavouriteScreen: Screen("favourite_screen")
}