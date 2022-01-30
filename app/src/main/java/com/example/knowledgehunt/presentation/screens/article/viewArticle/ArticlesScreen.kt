package com.example.knowledgehunt.presentation.screens.article.viewArticle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.models.Screens
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import compose.icons.TablerIcons
import compose.icons.tablericons.CodePlus

@Composable
fun Articles(openDrawer: () -> Unit, navController: NavHostController) {
    val viewModel: ArticleScreenViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    Scaffold(
        floatingActionButton = {

            Icon(
                imageVector = TablerIcons.CodePlus,
                tint = MaterialTheme.colors.secondary,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate(Screens.AddArticle.route)
                    },
            )
        },
    ) {

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() },
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn {
                items(30) { index ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        Alignment.Center
                    ) {
                        Text(text = index.toString(), textAlign = TextAlign.Center)
                    }
                }
            }
        }

    }
}