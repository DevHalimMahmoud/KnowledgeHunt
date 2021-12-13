package com.example.knowledgehunt.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.BackTopBar
import com.example.knowledgehunt.ui.components.Screens

@Composable
fun RegisterScreen(navController: NavHostController) {
    Scaffold(topBar = {
        BackTopBar(
            title = Screens.Register.title,
            buttonIcon = Icons.Default.ArrowBack,
            modifier = Modifier,
            onClick = { navController.popBackStack() }
        )
    }) {

    }
}