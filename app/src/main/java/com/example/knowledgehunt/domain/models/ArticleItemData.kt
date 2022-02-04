package com.example.knowledgehunt.domain.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class ArticleItemData(
    val user_id: String? = null,
    val title: String? = null,
    val description: String? = null,
    @ServerTimestamp
    val date: Timestamp? = null,
    val content: String? = null,
    val reactions: List<Int>? = null,
    val imageUrl: String? = null,
)


