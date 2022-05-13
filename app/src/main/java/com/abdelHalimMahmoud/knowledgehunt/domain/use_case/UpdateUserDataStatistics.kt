package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseFirestore

class UpdateUserDataStatistics(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(
        collection: String,
        id: String,
        data: MutableMap<String, Any?>
    ) = repository.addDataToDocument(collection, id, data)
}