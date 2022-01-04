package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseAuth


class GetCurrentUser(
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(

    ) = repository.getCurrentUser()
}