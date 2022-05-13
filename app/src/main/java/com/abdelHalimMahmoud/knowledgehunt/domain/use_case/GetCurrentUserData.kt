package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseFirestore

class GetCurrentUserData(private val repository: IFirebaseFirestore) {
    suspend operator fun invoke(

    ) = repository.getCurrentUserData()
}