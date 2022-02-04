package com.example.knowledgehunt.presentation.screens.article.articleDetailsScreen

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.knowledgehunt.presentation.components.ArticleTopBar

@Composable
fun ArticleDetailsScreen(
    navController: NavHostController
) {

    Scaffold(
        topBar = {
            ArticleTopBar(
                title = "a7aaaaa",
                profileImageUrl = Uri.parse("https://storage.googleapis.com/glaze-ecom.appspot.com/images/P2uSA0D_6/thumbs/232.png"),
                modifier = Modifier
                    .border(1.dp, color = MaterialTheme.colors.onError, CircleShape)
                    .clip(CircleShape),
                back = ({ navController.popBackStack() })
            )
        }) {

    }
}