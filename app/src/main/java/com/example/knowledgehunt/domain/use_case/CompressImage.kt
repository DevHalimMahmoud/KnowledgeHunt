package com.example.knowledgehunt.domain.use_case

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.MutableState
import com.example.knowledgehunt.domain.repository.IImage


class CompressImage(
    private val repository: IImage
) {
    suspend operator fun invoke(context: Context, imageUri: MutableState<Uri?>?) =
        repository.compressImage(context, imageUri)
}