package com.example.knowledgehunt.presentation.screens.article.viewArticle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.models.Screens
import com.example.knowledgehunt.domain.utils.ArticleArguments
import com.example.knowledgehunt.presentation.components.ArticleCardItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import compose.icons.TablerIcons
import compose.icons.tablericons.CodePlus


@Composable
fun Articles(navController: NavHostController) {
//    var shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Custom)
    val viewModel: ArticleScreenViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

//    DisposableEffect(key1 = true) {
//        onDispose {
//            ArticleArguments.instance?.destroy()
//        }
//    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {

            Icon(
                imageVector = TablerIcons.CodePlus,
                tint = MaterialTheme.colors.primary,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate(Screens.AddArticle.route)
                    },
            )
        },
//        modifier = Modifier.shimmer(customShimmer = null)
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                viewModel.refresh()
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {

                items(
                    viewModel.articleState.value

                ) { article ->
                    val author: MutableState<String> = remember {
                        mutableStateOf("Author")
                    }
                    if (author.value == "Author") {
                        LaunchedEffect(key1 = true) {
                            viewModel.getAuthorName(article, author)
//                            Log.d(TAG, "Articles: ${author.value}")
                        }
                    }

                    ArticleCardItem(
                        article,
                        author.value,
                        navController = navController,
                        click = {
                            ArticleArguments.instance?.author = author.value
                            ArticleArguments.instance?.articleItemData = article
                            navController.navigate(Screens.ArticleDetails.route)
                        }
                    )
                }
            }
        }
    }
}

