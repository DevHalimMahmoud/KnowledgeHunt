package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseFirestore

class GetArticles(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(
        collectionPath: String
    ) = repository.getCollectionFromFirestore(collectionPath)
}