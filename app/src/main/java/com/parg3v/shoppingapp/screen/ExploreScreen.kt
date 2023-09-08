package com.parg3v.shoppingapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ExploreScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Explore Screen", modifier = Modifier.align(Alignment.Center))
    }
}