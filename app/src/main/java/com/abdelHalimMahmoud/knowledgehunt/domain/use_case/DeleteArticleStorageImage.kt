package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseStorage

class DeleteArticleStorageImage(
    private val repository: IFirebaseStorage
) {
    suspend operator fun invoke(
        collection: String,
        id: String,
    ) = repository.deleteStorageImageUrl(collection, id)
}