package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseFirestore

class GetUserDataStatistics(private val repository: IFirebaseFirestore) {
    suspend operator fun invoke(
    ) = repository.getCurrentUserData()
}