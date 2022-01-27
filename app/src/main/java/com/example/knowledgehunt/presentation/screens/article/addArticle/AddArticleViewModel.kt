package com.example.knowledgehunt.presentation.screens.article.addArticle

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.use_case.ImageUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddArticleViewModel @Inject constructor(
    private val storageUseCases: StorageUseCases,
    private val imageUseCases: ImageUseCases,
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {

    val imageUri: MutableState<Uri?> = mutableStateOf(null)
    val bitmap: MutableState<Bitmap?> = mutableStateOf(null)

    val imageCompressionProgressIndicator: MutableState<Boolean> = mutableStateOf(false)

    val publishArticleProgressIndicator: MutableState<Boolean> = mutableStateOf(true)
    var publishError: MutableState<String> = mutableStateOf("Article Published Successfully!")
    var publishStates: MutableState<Boolean> = mutableStateOf(false)

    var titleState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var titleErrorState: MutableState<Boolean> = mutableStateOf(false)

    var descriptionState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var descriptionErrorState: MutableState<Boolean> = mutableStateOf(false)

    var contentState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var contentErrorState: MutableState<Boolean> = mutableStateOf(false)
    var mutableMap: HashMap<String, Any?> = hashMapOf()

    suspend fun compressProfileImage(context: Context) {
        bitmap.value = imageUseCases.compressImage(context = context, imageUri)
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
        mutableMap["user_id"] = FirebaseAuth.getInstance().currentUser?.uid!!


        return mutableMap
    }

    fun publishArticle() {
        viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            mutableMap = dataMap()
            firestoreUseCases.addArticleDataToFirestore(mutableMap)
                .addOnCompleteListener { task1 ->

                    if (task1.isSuccessful) {
                        viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
                            storageUseCases.uploadStorageImage(
                                bitmap.value!!,
                                "article",
                                task1.result.id
                            ).addOnCompleteListener { task2 ->
                                publishArticleProgressIndicator.value = true
                                publishStates.value = true
                                if (task2.isSuccessful) {
                                    publishError.value =
                                        "Article Published Successfully!"
                                } else {
                                    publishError.value =
                                        task2.exception?.localizedMessage.toString()

                                }
                            }
                        }
                    } else {
                        publishError.value = task1.exception?.localizedMessage.toString()
                        publishArticleProgressIndicator.value = true
                        publishStates.value = true
                    }
                }
        }

    }


}

