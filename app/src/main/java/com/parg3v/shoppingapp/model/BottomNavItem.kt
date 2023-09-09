package com.parg3v.shoppingapp.model

import com.parg3v.shoppingapp.R
import com.parg3v.shoppingapp.navigation.Screen

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val icon: Int,
    val hasNews: Boolean
) {
    object Home :
        BottomNavItem("Shop", Screen.HomeScreen.route, R.drawable.shop_icon, false)

    object Explore :
        BottomNavItem("Explore", Screen.ExploreScreen.route, R.drawable.explore_button, false)

    object Cart :
        BottomNavItem("Cart", Screen.CartScreen.route, R.drawable.cart_icon, true)

    object Favourite :
        BottomNavItem("Favourite", Screen.FavouriteScreen.route, R.drawable.favourite_icon, false)
}
