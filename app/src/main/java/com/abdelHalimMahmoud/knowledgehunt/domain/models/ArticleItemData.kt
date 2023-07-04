package com.abdelHalimMahmoud.knowledgehunt.domain.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import java.text.SimpleDateFormat
import java.util.Locale

data class ArticleItemData(
    val user_id: String? = null,
    val title: String? = null,
    val description: String? = null,
    @ServerTimestamp
    val date: Timestamp? = null,
    val content: String? = null,
    val reactions: List<Int>? = null,
    val imageUrl: String? = null,
) {
    fun formattedDate(): String {
        val df = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
        try {
            df.format(date?.toDate()?.time).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}


