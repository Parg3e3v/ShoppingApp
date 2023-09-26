package com.parg3v.shoppingapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.parg3v.domain.model.Product
import com.parg3v.shoppingapp.view.cart.CartScreen
import com.parg3v.shoppingapp.view.explore.ExploreScreen
import com.parg3v.shoppingapp.view.favourite.FavouriteScreen
import com.parg3v.shoppingapp.view.home.HomeScreen
import com.parg3v.shoppingapp.view.info.InfoScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(
            route = Screen.HomeScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            }

        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.InfoScreen.route + "/{product}",

            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            }
        ) { navBackStackEntry ->

            val gson: Gson = GsonBuilder().create()
            val productJson = navBackStackEntry.arguments?.getString("product")
            val productObject = gson.fromJson(productJson, Product::class.java)

            InfoScreen(productObject)
        }
        composable(route = Screen.ExploreScreen.route) {
            ExploreScreen()
        }
        composable(route = Screen.CartScreen.route) {
            CartScreen()
        }
        composable(route = Screen.FavouriteScreen.route) {
            FavouriteScreen()
        }
    }
}