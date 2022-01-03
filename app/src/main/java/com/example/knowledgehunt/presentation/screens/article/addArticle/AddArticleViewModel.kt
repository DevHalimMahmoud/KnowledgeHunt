package com.example.knowledgehunt.presentation.screens.article.addArticle

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.data.repository.FirebaseAuthServices
import com.example.knowledgehunt.data.repository.FirebaseFirestoreServices
import com.example.knowledgehunt.data.repository.FirebaseStorageServices
import com.example.knowledgehunt.data.repository.ImageServices
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddArticleViewModel(


    val imageUri: MutableState<Uri?>? = mutableStateOf(null),
    val bitmap: MutableState<Bitmap?> = mutableStateOf(null),

    val ImageCompressionProgressIndicator: MutableState<Boolean> = mutableStateOf(false),

    val PublishArticleProgressIndicator: MutableState<Boolean> = mutableStateOf(true),
    var PublishError: MutableState<String> = mutableStateOf("Article Published Successfully!"),
    var PublishStates: MutableState<Boolean> = mutableStateOf(false),

    var titleState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var titleErrorState: MutableState<Boolean> = mutableStateOf(false),

    var descriptionState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var descriptionErrorState: MutableState<Boolean> = mutableStateOf(false),

    var contentState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var contentErrorState: MutableState<Boolean> = mutableStateOf(false),
    var mutableMap: HashMap<String, Any?> = hashMapOf()

) : ViewModel() {

    suspend fun compressProfileImage(context: Context) {
        bitmap.value = ImageServices.compressImage(context = context, imageUri)
    }

    fun notEmpty(): Boolean {
        return titleState.value.text.isNotBlank() && descriptionState.value.text.isNotBlank() && contentState.value.text.isNotBlank() &&
                bitmap.value != null
    }

    private suspend fun dataMap(): HashMap<String, Any?> {
        mutableMap["content"] = contentState.value.text
        mutableMap["date"] = java.sql.Timestamp(System.currentTimeMillis())
        mutableMap["description"] = descriptionState.value.text
        mutableMap["reactions"] = listOf(0, 0, 0, 0, 0)
        mutableMap["title"] = titleState.value.text
        mutableMap["user_id"] = FirebaseAuthServices.getCurrentUserId()


        return mutableMap
    }

    fun publishArticle() {
        viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            mutableMap = dataMap()
            FirebaseFirestoreServices.addArticleDataToFirestore(mutableMap)
                .addOnCompleteListener { task1 ->

                    if (task1.isSuccessful) {
                        viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
                            FirebaseStorageServices.uploadProfileImage(
                                bitmap.value!!,
                                "article",
                                task1.result.id
                            ).addOnCompleteListener { task2 ->
                                PublishArticleProgressIndicator.value = true
                                PublishStates.value = true
                                if (task2.isSuccessful) {
                                    PublishError.value =
                                        "Article Published Successfully!"
                                } else {
                                    PublishError.value =
                                        task2.exception?.localizedMessage.toString()

                                }
                            }
                        }
                    } else {
                        PublishError.value = task1.exception?.localizedMessage.toString()
                        PublishArticleProgressIndicator.value = true
                        PublishStates.value = true
                    }
                }
        }

    }


}

