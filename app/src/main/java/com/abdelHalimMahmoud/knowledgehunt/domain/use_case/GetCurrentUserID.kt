package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseAuth

class GetCurrentUserID(
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(

    ) = repository.getCurrentUserId()
}