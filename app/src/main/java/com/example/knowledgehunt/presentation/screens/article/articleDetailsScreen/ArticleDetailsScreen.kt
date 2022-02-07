package com.example.knowledgehunt.presentation.screens.article.articleDetailsScreen

import android.widget.TextView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.utils.ArticleArguments
import com.example.knowledgehunt.presentation.components.ArticleTopBar

@Composable
fun ArticleDetailsScreen(
    navController: NavHostController,
) {
    val viewModel: ArticleDetailsScreenViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            ArticleTopBar(
                title = ArticleArguments.instance?.author,
                profileImageUrl = viewModel.authorImageUrl.value,
                modifier = Modifier
                    .border(1.dp, color = MaterialTheme.colors.onError, CircleShape)
                    .clip(CircleShape),
                back = ({ navController.popBackStack() })
            )
        }) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            AndroidView(
                modifier = Modifier,
                factory = { context -> TextView(context) },
                update = {
                    it.text = HtmlCompat.fromHtml(
                        ArticleArguments.instance?.articleItemData?.content!!,
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                }
            )
        }
    }
}