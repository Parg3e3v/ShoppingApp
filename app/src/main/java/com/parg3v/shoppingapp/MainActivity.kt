@file:OptIn(ExperimentalMaterial3Api::class)

package com.parg3v.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.parg3v.shoppingapp.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
        viewModel.state.observe(this) {
            println("[LOG] loading data.....")
            if (!it.isLoading) {
                if (it.error.isNotBlank())
                    println(it.error)
                else {
                    println("[LOG] Success! your data is -> ${it.products}")
                }
            }
        }
    }
}


