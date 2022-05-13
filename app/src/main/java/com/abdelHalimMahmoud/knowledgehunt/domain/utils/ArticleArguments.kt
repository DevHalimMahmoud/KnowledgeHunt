package com.abdelHalimMahmoud.knowledgehunt.domain.utils

import com.abdelHalimMahmoud.knowledgehunt.domain.models.ArticleItemData

// This is NOT the best way to pass args when navigating from composable to another
// HOWEVER due to the limitation of the recommended way i was forced to do this may god forgive me
class ArticleArguments private constructor() {
    var articleItemData: ArticleItemData? = null
    var author: String? = null

    fun destroy() {
        articleItemData = null
        author = null
        instance = null
    }

    companion object {
        var instance: ArticleArguments? = null
            get() {
                if (field == null) {
                    field = ArticleArguments()
                }
                return field
            }
            private set
    }
}