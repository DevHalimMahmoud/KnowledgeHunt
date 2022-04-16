package com.example.knowledgehunt.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.presentation.components.SearchTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painterResource(R.drawable.ic_search),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
                    .padding(12.dp),
            )
        }
    }
}