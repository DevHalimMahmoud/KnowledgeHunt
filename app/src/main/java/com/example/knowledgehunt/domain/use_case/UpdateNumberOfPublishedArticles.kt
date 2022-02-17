package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore

class UpdateNumberOfPublishedArticles(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(
        collection: String,
        id: String,
        data: MutableMap<String, Any?>
    ) = repository.addDataToDocument(collection, id, data)
}