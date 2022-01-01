package com.example.knowledgehunt.viewModels

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.ImageServices

class AddArticleViewModel(


    val imageUri: MutableState<Uri?>? = mutableStateOf(null),
    val bitmap: MutableState<Bitmap?> = mutableStateOf(null),

    val ImageCompressionProgressIndicator: MutableState<Boolean> = mutableStateOf(false),

    val PublishArticleProgressIndicator: MutableState<Boolean> = mutableStateOf(true),
    var PublishError: MutableState<String> = mutableStateOf("Article Published Successfully!"),
    var PublishStates: MutableState<Boolean> = mutableStateOf(false),


    ) : ViewModel() {

    suspend fun compressProfileImage(context: Context) {
        bitmap.value = ImageServices.compressImage(context = context, imageUri)
    }


    fun notEmpty(): Boolean {

        return true
    }


}

