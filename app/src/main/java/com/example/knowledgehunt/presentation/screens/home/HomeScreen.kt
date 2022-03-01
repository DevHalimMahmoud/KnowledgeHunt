package com.example.knowledgehunt.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.presentation.components.NoDataDesign


@Composable
fun HomeScreen(navController: NavHostController) {

    Scaffold(
        topBar = {

        },

        ) {

        NoDataDesign(
            title = "This part of the app is still being developed",
            image = painterResource(R.drawable.ic_empty),
        )

    }

}