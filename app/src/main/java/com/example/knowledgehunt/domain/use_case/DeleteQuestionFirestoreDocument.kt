package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore

class DeleteQuestionFirestoreDocument(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(
        collection: String,
        id: String,
    ) =
        repository.deleteDocument(collection, id)
}