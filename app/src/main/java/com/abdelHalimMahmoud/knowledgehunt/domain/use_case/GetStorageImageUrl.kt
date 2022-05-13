package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseStorage

class GetStorageImageUrl(
    private val repository: IFirebaseStorage
) {
    suspend operator fun invoke(
        bath: String,
        name: String
    ) = repository.getStorageImageUrl(bath, name)

}