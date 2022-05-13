package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import android.graphics.Bitmap
import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseStorage

class UploadStorageImage(
    private val repository: IFirebaseStorage
) {
    suspend operator fun invoke(
        bitmap: Bitmap,
        bath: String,
        name: String
    ) =
        repository.uploadStorageImage(bitmap, bath, name)
}