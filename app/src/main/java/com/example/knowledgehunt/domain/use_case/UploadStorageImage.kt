package com.example.knowledgehunt.domain.use_case

import android.graphics.Bitmap
import com.example.knowledgehunt.domain.repository.IFirebaseStorage

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