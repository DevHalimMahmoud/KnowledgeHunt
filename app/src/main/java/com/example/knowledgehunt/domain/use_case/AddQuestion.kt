package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore

class AddQuestion(
    private val repository: IFirebaseFirestore
) {
    suspend operator fun invoke(collection: String,

                                data: MutableMap<String, Any?>) =
        repository.addData(collection,data)
}