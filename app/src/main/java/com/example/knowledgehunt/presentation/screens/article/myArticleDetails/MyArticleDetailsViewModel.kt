package com.example.knowledgehunt.presentation.screens.article.myArticleDetails

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import com.example.knowledgehunt.domain.utils.ArticleArguments
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyArticleDetailsViewModel @Inject constructor(

    private val firestoreUseCases: FirestoreUseCases,
    private val storageUseCases: StorageUseCases,
) : ViewModel() {


    val publishArticleProgressIndicator: MutableState<Boolean> = mutableStateOf(true)
    var publishError: MutableState<String> = mutableStateOf("Article Updated Successfully!")
    var publishStates: MutableState<Boolean> = mutableStateOf(false)

    var titleState: MutableState<TextFieldValue> =
        mutableStateOf(TextFieldValue(ArticleArguments.instance?.articleItemData?.title ?: "Title"))

    var titleErrorState: MutableState<Boolean> = mutableStateOf(false)

    var descriptionState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue(
            ArticleArguments.instance?.articleItemData?.description ?: "Description"
        )
    )
    var descriptionErrorState: MutableState<Boolean> = mutableStateOf(false)

    var contentState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue(
            ArticleArguments.instance?.articleItemData?.content ?: "Article Content"
        )
    )
    var contentErrorState: MutableState<Boolean> = mutableStateOf(false)
    var mutableMap: HashMap<String, Any?> = hashMapOf()

    private var _articleId: MutableState<String?> = mutableStateOf(null)


    private var _deleteImageState: MutableState<Boolean> = mutableStateOf(false)
    var deleteImageState: State<Boolean> = _deleteImageState

    private var _deleteDocumentState: MutableState<Boolean> = mutableStateOf(false)
    var deleteDocumentState: State<Boolean> = _deleteDocumentState


    init {
        getArticleId()
    }

    fun deleteArticle(navController: NavController) {

        _deleteDocumentState.value = true
        _deleteImageState.value = true
        viewModelScope.launch {
            deleteFirestoreArticleDocument()
            deleteStorageDocumentImage()
            updateNumberOfPublishedArticles()
            delay(1000)
            navController.popBackStack()

        }
    }

    private fun deleteFirestoreArticleDocument() {
        viewModelScope.launch {
            firestoreUseCases.deleteArticleFirestoreDocument(
                "articles",
                _articleId.value.toString()
            ).addOnCompleteListener {
                _deleteDocumentState.value = false
            }
        }
    }

    private fun deleteStorageDocumentImage() {
        viewModelScope.launch {
            storageUseCases.deleteArticleStorageImage("article", _articleId.value.toString())
                .addOnCompleteListener {
                    _deleteImageState.value = false
                }
        }
    }

    fun notEmpty(): Boolean {
        return titleState.value.text.isNotBlank() && descriptionState.value.text.isNotBlank() && contentState.value.text.isNotBlank()
    }

    private suspend fun dataMap(): HashMap<String, Any?> {
        mutableMap["content"] = contentState.value.text
        mutableMap["description"] = descriptionState.value.text
        mutableMap["title"] = titleState.value.text
        return mutableMap
    }

    fun updateArticle() {

        if (_articleId.value != null) {
            viewModelScope.launch {
                firestoreUseCases.updateArticleViewsCount(
                    "articles",
                    _articleId.value!!,
                    dataMap()
                ).addOnCompleteListener {
                    publishArticleProgressIndicator.value = true
                    publishStates.value = true
                    publishError.value =
                        "Article Updated Successfully!"
                }
            }
        }
    }

    private fun updateNumberOfPublishedArticles() {
        viewModelScope.launch {
            firestoreUseCases.getUserDataStatistics().addOnCompleteListener {
                if (it.isSuccessful) {

                    val mutableMap: HashMap<String, Any?> = hashMapOf()
                    mutableMap["num_articles"] = it.result.get("num_articles") as Long - 1
                    mutableMap["score"] = it.result.get("score") as Long - 10
                    viewModelScope.launch {
                        firestoreUseCases.updateUserDataStatistics(
                            "user",
                            FirebaseAuth.getInstance().currentUser?.uid.toString(),
                            mutableMap
                        )
                    }
                }
            }
        }
    }

    private fun getArticleId() {

        viewModelScope.launch {
            firestoreUseCases.getMyArticleId(
                "articles",
                ArticleArguments.instance?.articleItemData?.user_id!!,
                "user_id",
                "date",
                ArticleArguments.instance?.articleItemData?.date
            ).addOnCompleteListener {
                _articleId.value = it.result.documents[0].id
                Log.d(ContentValues.TAG, "updateReactions: ${_articleId.value}")
            }
        }
    }

}