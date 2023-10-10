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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.parg3v.shoppingapp.view.cart.CartScreen
import com.parg3v.shoppingapp.view.explore.ExploreScreen
import com.parg3v.shoppingapp.view.favourite.FavouriteScreen
import com.parg3v.shoppingapp.view.grid.GridScreen
import com.parg3v.shoppingapp.view.home.HomeScreen
import com.parg3v.shoppingapp.view.info.InfoScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
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
        composable(route = "${Screen.InfoScreen.route}/{productId}",
            arguments = listOf(navArgument("productId") {
                type = NavType.StringType
                defaultValue = "-1"
                nullable = false
            }),

            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            }) { entry ->
            val productId = entry.arguments?.getString("productId")

            productId?.let {
                InfoScreen(
                    productId = it,
                    navController = navController,
                    snackbarHostState = snackbarHostState
                )
            }
        }
        composable(route = "${Screen.GridScreen.route}/{listDefinition}",
            arguments = listOf(navArgument("listDefinition") {
                type = NavType.StringType
                defaultValue = "-1"
                nullable = false
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 }, animationSpec = tween(
                        durationMillis = 300, easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            }) { entry ->

            entry.arguments?.getString("listDefinition")?.let { GridScreen(it, navController) }
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