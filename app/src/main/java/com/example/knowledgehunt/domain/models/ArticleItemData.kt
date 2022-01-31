package com.example.knowledgehunt.domain.models

import com.google.firebase.Timestamp

data class ArticleItemData(
    val user_id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val date: Timestamp? = null,
    val content: String? = null,
    val reactions: List<Int>? = null,
//    val imageUrl: Uri
)


