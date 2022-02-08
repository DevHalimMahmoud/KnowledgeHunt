package com.example.knowledgehunt.presentation.screens.article.articleDetailsScreen

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import com.example.knowledgehunt.domain.utils.ArticleArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsScreenViewModel @Inject constructor(
    private val storageUseCases: StorageUseCases,
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {
    private val _authorImageUrl: MutableState<Uri> =
        mutableStateOf(Uri.parse("https://storage.googleapis.com/glaze-ecom.appspot.com/images/P2uSA0D_6/thumbs/232.png"))

    val authorImageUrl: State<Uri> = _authorImageUrl

    private var _reactionsList: MutableList<Int> = mutableListOf()
    private var _articleId: MutableState<String?> = mutableStateOf(null)


    init {
        getAuthorImageUrl()
        getReactionsList()
    }

    private fun getAuthorImageUrl() {
        viewModelScope.launch {
            storageUseCases.getStorageImageUrl(
                "user",
                ArticleArguments.instance?.articleItemData?.user_id!!
            ).addOnCompleteListener {
                _authorImageUrl.value = it.result
            }
        }
    }

    fun updateReactionsList() {
        if (_articleId.value != null) {
            val mutableMap: MutableMap<String, Any?> = mutableMapOf()

            _reactionsList[0] = _reactionsList[0] + 1
            mutableMap["reactions"] = _reactionsList.toList()
            viewModelScope.launch {
                firestoreUseCases.updateArticleViewsCount(
                    "articles",
                    _articleId.value!!,
                    mutableMap
                )
            }
            Log.d(TAG, "updateReactions: $_reactionsList")
        }
    }

    private fun getReactionsList() {

        viewModelScope.launch {
            firestoreUseCases.getArticleViewsCount(
                "articles",
                ArticleArguments.instance?.articleItemData?.user_id!!,
                "user_id",
                "date",
                ArticleArguments.instance?.articleItemData?.date
            ).addOnCompleteListener {

                _reactionsList = it.result.documents[0].get("reactions") as MutableList<Int>
                _articleId.value = it.result.documents[0].id
                Log.d(TAG, "updateReactions: ${_articleId.value}")
                updateReactionsList()
            }
        }
    }

}