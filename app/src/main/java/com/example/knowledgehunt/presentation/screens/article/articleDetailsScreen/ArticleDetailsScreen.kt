package com.example.knowledgehunt.presentation.screens.article.articleDetailsScreen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.knowledgehunt.presentation.components.BackTopBar
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack

@Composable
fun ArticleDetailsScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            BackTopBar(
                title = "title",
                buttonIcon = TablerIcons.ArrowBack,
                modifier = Modifier,
                ({ navController.popBackStack() })
            )
        }) {

    }
}