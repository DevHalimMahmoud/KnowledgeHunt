package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.article.myArticles

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abdelHalimMahmoud.knowledgehunt.R
import com.abdelHalimMahmoud.knowledgehunt.domain.models.Screens
import com.abdelHalimMahmoud.knowledgehunt.domain.utils.ArticleArguments
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.ArticleCardItem
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.BackTopBar
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.NoDataDesign
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack
import compose.icons.tablericons.CodePlus

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyArticles(navController: NavHostController) {
//    var shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Custom)
    val viewModel: MyArticlesViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

//    DisposableEffect(key1 = true) {
//        onDispose {
//            ArticleArguments.instance?.destroy()
//        }
//    }
    Scaffold(
        topBar = {
            BackTopBar(
                title = Screens.MyArticles.title,
                buttonIcon = TablerIcons.ArrowBack,
                modifier = Modifier, onClick = { navController.popBackStack() }
            )
        },

        floatingActionButton = {

            Icon(
                imageVector = TablerIcons.CodePlus,
                tint = MaterialTheme.colors.primaryVariant,
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
                .fillMaxWidth()
        ) {

            if (viewModel.articleState.value.isEmpty()) {
                NoDataDesign(
                    title = "You don't have any published articles yet",
                    image = painterResource(R.drawable.ic_empty),
                )
            } else {
                LazyColumn(state = rememberLazyListState()) {

                    items(viewModel.articleState.value) { article ->
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
                                navController.navigate(Screens.MyArticlesDetails.route)
                            }
                        )
                    }
                }
            }
        }
    }
}