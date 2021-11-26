package com.example.knowledgehunt.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.TopBar

@Composable
fun About(openDrawer: () -> Unit, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "About",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = {
                openDrawer()
                navController.popBackStack()
            }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Help.", style = MaterialTheme.typography.h4)
        }
    }
}