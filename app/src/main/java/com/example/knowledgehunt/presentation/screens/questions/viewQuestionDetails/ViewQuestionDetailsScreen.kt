package com.example.knowledgehunt.presentation.screens.questions.viewQuestionDetails

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.knowledgehunt.presentation.components.BackTopBar
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack

@Composable
fun ViewQuestionDetailsScreen(navController: NavController) {
    Scaffold(topBar = {
        BackTopBar(
            title = "Question Details",
            buttonIcon = TablerIcons.ArrowBack,
            modifier = Modifier,
            onClick = { navController.popBackStack() },

            )
    }) {

    }
}