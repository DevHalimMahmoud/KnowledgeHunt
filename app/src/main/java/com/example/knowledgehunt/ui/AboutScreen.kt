package com.example.knowledgehunt.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.Screens

@Composable
fun About(openDrawer: () -> Unit, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "About Screen", style = MaterialTheme.typography.h4)
            Button(onClick = { navController.navigate(Screens.Help.route) }) {
                Text(text = "Help Screen")

            }
        }
    }
}