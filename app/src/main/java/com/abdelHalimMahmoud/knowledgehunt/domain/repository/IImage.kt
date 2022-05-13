package com.abdelHalimMahmoud.knowledgehunt.domain.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState

interface IImage {

    suspend fun compressImage(context: Context, imageUri: MutableState<Uri?>?): Bitmap?
}