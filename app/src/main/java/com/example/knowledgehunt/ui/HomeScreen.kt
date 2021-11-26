package com.example.knowledgehunt.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.TopBar


@Composable
fun HomeScreen(openDrawer: () -> Unit, navController: NavHostController) {

    Scaffold(
        topBar = {
            TopBar(
                title = "Home",
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = {
                    openDrawer()
                    navController.popBackStack()
                }
            )
        },

    ) {
        /* Add code later */
    }

}