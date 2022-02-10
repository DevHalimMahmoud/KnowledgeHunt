package com.example.knowledgehunt.presentation.screens.article.myArticleDetails

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.utils.ArticleArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyArticleDetailsViewModel @Inject constructor(

    private val firestoreUseCases: FirestoreUseCases,
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

    init {
        getArticleId()
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