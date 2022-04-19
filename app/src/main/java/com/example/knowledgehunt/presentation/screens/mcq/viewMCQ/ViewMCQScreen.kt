package com.example.knowledgehunt.presentation.screens.mcq.viewMCQ

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.domain.models.Screens
import com.example.knowledgehunt.domain.utils.MCQTestArguments
import com.example.knowledgehunt.presentation.components.MCQCardItem
import com.example.knowledgehunt.presentation.components.NoDataDesign
import com.example.knowledgehunt.presentation.components.SimpleAlertDialog
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ViewMCQScreen(navController: NavHostController) {
    val viewModel: ViewMCQScreenViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
    ) {
        SimpleAlertDialog(viewModel.showDialog, "Are you sure you want to start the test?") {
            viewModel.showDialog.value = false
            navController.navigate(
                Screens.TakeMCQ.route
            )
        }
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                viewModel.refresh()
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (viewModel.MCQState.value.isEmpty()) {
                NoDataDesign(
                    title = "No MCQ tests available at the moment check your internet connection",
                    image = painterResource(R.drawable.ic_empty),
                )
            } else {
                LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {

                    items(
                        viewModel.MCQState.value
                    ) { MCQItem ->
                        viewModel.selectedItem = MCQItem
                        MCQCardItem(
                            MCQItem,
                            navController = navController,
                            click = {
                                viewModel.showDialog.value = true

                                MCQTestArguments.instance?.itemData = MCQItem

                            }
                        )
                    }
                }
            }
        }
    }
}