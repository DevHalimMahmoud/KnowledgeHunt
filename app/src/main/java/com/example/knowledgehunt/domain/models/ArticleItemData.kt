package com.example.knowledgehunt.domain.models

import android.net.Uri

data class ArticleItemData(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: Uri
)


