package com.example.knowledgehunt.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.models.MCQItemData

@Composable
fun MCQCardItem(article: MCQItemData, navController: NavHostController, click: () -> Unit) {
    Text(text = article.title.toString(), modifier = Modifier.padding())
}
