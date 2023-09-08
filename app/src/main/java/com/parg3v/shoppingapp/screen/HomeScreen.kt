@file:OptIn(ExperimentalMaterial3Api::class)

package com.parg3v.shoppingapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.parg3v.shoppingapp.components.ShoppingItemRow
import com.parg3v.shoppingapp.navigation.Screen
import com.parg3v.shoppingapp.ui.theme.ShoppingAppTheme


@Composable
fun HomeScreen(navController: NavController) {
    ShoppingAppTheme {
        var presses = remember { mutableStateOf(0) }

        Scaffold(
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Button(
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            navController.navigate(Screen.ExploreScreen.route)
                        }) {
                        Text(text = "Navigate")
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { presses.value++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShoppingItemRow()
            }
        }
    }
}