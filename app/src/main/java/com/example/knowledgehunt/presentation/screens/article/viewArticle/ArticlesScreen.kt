package com.example.knowledgehunt.presentation.screens.article.viewArticle

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.models.ArticleItemData
import com.example.knowledgehunt.domain.models.Screens
import com.example.knowledgehunt.presentation.components.ArticleCardItem
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

        ArticleCardItem(
            articleItemData = ArticleItemData(
                "1",
                "Title",
                "Subtitle",
                Uri.parse("https://www.fixservis.sk/media/filer_public/de/fe/defe8629-6cf0-4d68-b7c5-5a43ce3819f2/denny-muller-hfwa-axq6ek-unsplash.jpg")
            )
        )

//        SwipeRefresh(
//            state = rememberSwipeRefreshState(isRefreshing),
//            onRefresh = { viewModel.refresh() },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            LazyColumn {
//                items(30) { index ->
//                    Box(
//                        modifier = Modifier.fillMaxWidth(),
//                        Alignment.Center
//                    ) {
//                        Text(text = index.toString(), textAlign = TextAlign.Center)
//                    }
//                }
//            }
//        }

    }
}