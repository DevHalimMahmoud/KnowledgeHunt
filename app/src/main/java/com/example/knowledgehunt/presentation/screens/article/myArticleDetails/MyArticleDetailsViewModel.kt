package com.example.knowledgehunt.presentation.screens.article.myArticleDetails

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
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.storage.UploadTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyArticleDetailsViewModel @Inject constructor(
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
        mutableMap["reactions"] = listOf(0, 0, 0, 0, 0, 0)
        mutableMap["title"] = titleState.value.text
        mutableMap["user_id"] = FirebaseAuth.getInstance().currentUser?.uid!!


        return mutableMap
    }

    fun publishArticle() {
        viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            mutableMap = dataMap()
            firestoreUseCases.addArticleDataToFirestore(mutableMap)
                .addOnCompleteListener { task1 ->
                    uploadImage(task1)

                }
        }

    }

    private fun uploadImage(task1: Task<DocumentReference>) {
        if (task1.isSuccessful) {
            viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
                storageUseCases.uploadStorageImage(
                    bitmap.value!!,
                    "article",
                    task1.result.id
                ).addOnCompleteListener { task2 ->

                    getStorageImageUrl(task2, task1)
                }
            }
        } else {
            publishError.value = task1.exception?.localizedMessage.toString()
            publishArticleProgressIndicator.value = true
            publishStates.value = true
        }

    }

    private fun getStorageImageUrl(
        task2: Task<UploadTask.TaskSnapshot>,
        task1: Task<DocumentReference>
    ) {
        if (task2.isSuccessful) {
            viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
                task2.result.storage.downloadUrl.addOnSuccessListener {
                    viewModelScope.launch(
                        Dispatchers.IO,
                        CoroutineStart.DEFAULT
                    ) {
                        firestoreUseCases.addImageUrlToArticleDocument(
                            "articles", task1.result.id,
                            hashMapOf("imageUrl" to it.toString())
                        ).addOnCompleteListener {
                            publishArticleProgressIndicator.value = true
                            publishStates.value = true
                            publishError.value =
                                "Article Published Successfully!"
                        }

                    }
                }
            }
        } else {
            publishError.value =
                task2.exception?.localizedMessage.toString()

        }

    }

}