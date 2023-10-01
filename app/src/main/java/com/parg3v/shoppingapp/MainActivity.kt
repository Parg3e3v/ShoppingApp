package com.parg3v.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.parg3v.shoppingapp.components.CustomScaffold
import com.parg3v.shoppingapp.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberBottomSheetScaffoldState()
            val navController: NavHostController = rememberAnimatedNavController()
            CustomScaffold(navController = navController) {paddingValues ->
                Navigation(navController, paddingValues, scaffoldState)
            }
        }
    }
}


