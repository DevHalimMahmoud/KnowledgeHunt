package com.example.knowledgehunt.domain.models

data class MCQItemData(
    val title: String? = null,
    val language: String? = null,
    val image_url: String? = null,
    var answers: MutableList<HashMap<String, String>?>? = null,
    val try_num: Long? = null,
)
