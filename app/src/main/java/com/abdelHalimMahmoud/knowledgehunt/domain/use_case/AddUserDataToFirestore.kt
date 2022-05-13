package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseFirestore

class AddUserDataToFirestore(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(data: MutableMap<String, Any?>) =
        repository.addUserDataToFirestore(data)
}