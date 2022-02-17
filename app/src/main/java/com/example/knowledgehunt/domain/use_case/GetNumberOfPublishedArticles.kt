package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore

class GetNumberOfPublishedArticles(private val repository: IFirebaseFirestore) {
    suspend operator fun invoke(
    ) = repository.getCurrentUserData()
}