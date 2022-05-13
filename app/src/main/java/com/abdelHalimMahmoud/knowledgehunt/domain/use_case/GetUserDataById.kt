package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseFirestore

class GetUserDataById(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(
        collection: String,
        id: String,
    ) = repository.getDocumentById(collection, id)
}