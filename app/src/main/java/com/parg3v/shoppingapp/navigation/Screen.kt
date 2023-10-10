package com.parg3v.shoppingapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object InfoScreen : Screen("info_screen")
    object GridScreen : Screen("grid_screen")
    object ExploreScreen : Screen("explore_screen")
    object CartScreen : Screen("cart_screen")
    object FavouriteScreen : Screen("favourite_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}