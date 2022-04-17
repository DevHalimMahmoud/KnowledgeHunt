package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore

class GetMCQTests(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(
        collectionPath: String
    ) = repository.getCollectionFromFirestore(collectionPath)
}