package com.parg3v.shoppingapp.navigation

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.parg3v.domain.model.Product

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object InfoScreen : Screen("info_screen")
    object ExploreScreen : Screen("explore_screen")
    object CartScreen : Screen("cart_screen")
    object FavouriteScreen : Screen("favourite_screen")

    fun withArgsFromProduct(item: Product): String {
        val gson: Gson = GsonBuilder().create()
        val itemJson = gson.toJson(item)
        return withArgs(Uri.encode(itemJson))
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}