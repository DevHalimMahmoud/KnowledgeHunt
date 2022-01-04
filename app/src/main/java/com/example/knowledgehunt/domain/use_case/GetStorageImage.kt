package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseStorage

class GetStorageImage(
    private val repository: IFirebaseStorage
) {
    suspend operator fun invoke(
        bath: String,
        name: String
    ) = repository.getStorageImage(bath, name)

}