package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore

class GetCurrentUserData(private val repository: IFirebaseFirestore) {
    suspend operator fun invoke(

    ) = repository.getCurrentUserData()
}