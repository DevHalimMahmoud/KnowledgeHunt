package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.article.viewArticle

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abdelHalimMahmoud.knowledgehunt.R
import com.abdelHalimMahmoud.knowledgehunt.domain.models.Screens
import com.abdelHalimMahmoud.knowledgehunt.domain.utils.ArticleArguments
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.ArticleCardItem
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.NoDataDesign
import compose.icons.TablerIcons
import compose.icons.tablericons.CodePlus

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Articles(navController: NavHostController) {
    val viewModel: ArticleScreenViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

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
    ) {
//        SwipeRefresh(
//            state = rememberSwipeRefreshState(isRefreshing),
//            onRefresh = {
//                viewModel.refresh()
//            },
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
        if (viewModel.articleState.value.isEmpty()) {
            NoDataDesign(
                title = "No articles available at the moment check your internet connection",
                image = painterResource(R.drawable.ic_empty),
            )
        } else {
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
//}

