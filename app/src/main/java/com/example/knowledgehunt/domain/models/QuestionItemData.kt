package com.example.knowledgehunt.domain.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp

data class QuestionItemData(
    val views: Long? = null,
    val user_id: String? = null,
    val title: String? = null,
    val question_upvotes: Long? = null,
    val question_downvotes: Long? = null,
    val prog_language: String? = null,
    val field: String? = null,
    @ServerTimestamp
    val date: Timestamp? = null,
    val content: String? = null,
    val answers: List<HashMap<String, Any>>? = null,
)