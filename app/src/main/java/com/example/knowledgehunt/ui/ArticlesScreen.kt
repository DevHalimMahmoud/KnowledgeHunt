package com.example.knowledgehunt.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.Screens
import com.example.knowledgehunt.ui.components.TopBar

@Composable
fun Articles(openDrawer: () -> Unit, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Articles",
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = {
                    openDrawer()
                    navController.popBackStack()
                }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Account.", style = MaterialTheme.typography.h4)
                Button(onClick = { navController.navigate(Screens.Test.route) }) {

                }
            }
        }
    }

}