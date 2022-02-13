package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseStorage

class DeleteArticleStorageImage(
    private val repository: IFirebaseStorage
) {
    suspend operator fun invoke(
        collection: String,
        id: String,
    ) = repository.deleteStorageImageUrl(collection, id)
}