package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore

class AddArticleDataToFirestore (
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(data: MutableMap<String, Any?>) =
        repository.addArticleDataToFirestore(data)
}