package com.example.knowledgehunt.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.Screens

@Composable
fun LoginScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            Text(text = "Login")

        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = { navController.navigate(Screens.Home.route) }) {
                    Text(text = "Login")
                }
            }
        }
    }

}