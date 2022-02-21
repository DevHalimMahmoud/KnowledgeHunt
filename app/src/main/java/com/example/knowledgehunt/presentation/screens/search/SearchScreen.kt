package com.example.knowledgehunt.presentation.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.presentation.components.SearchTopBar
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel: SearchScreenViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            SearchTopBar(
                query = viewModel.query.value,
                onQueryChanged = viewModel::onQueryChanged,
                onExecuteSearch = {

                },
                back = { navController.popBackStack() }
            )
        }
    ) {

    }
}