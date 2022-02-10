package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore

class GetMyArticles(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(
        collectionPath: String,
    ) = repository.getCollectionFromFirestore(collectionPath)
}