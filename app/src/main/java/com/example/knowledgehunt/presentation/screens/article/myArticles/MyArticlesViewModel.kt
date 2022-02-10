package com.example.knowledgehunt.presentation.screens.article.myArticles

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.models.ArticleItemData
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyArticlesViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(true)
    var document: DocumentSnapshot? = null
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _articleState = mutableStateOf<List<ArticleItemData>>(listOf())
    val articleState: State<List<ArticleItemData>> = _articleState

    init {
        getArticles()
    }

    fun refresh() {
        // This doesn't handle multiple 'refreshing' tasks, don't use this
        viewModelScope.launch {
            firestoreUseCases.getMyArticles("articles")
                .whereEqualTo("user_id", FirebaseAuth.getInstance().currentUser?.uid!!)
                .addSnapshotListener { snapshot, e ->
                    _articleState.value =
                        snapshot?.toObjects(ArticleItemData::class.java) as List<ArticleItemData>
                }
            // A fake 2 second 'refresh'
            _isRefreshing.emit(true)
            delay(2000)
            _isRefreshing.emit(false)
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            firestoreUseCases.getMyArticles("articles")
                .whereEqualTo("user_id", FirebaseAuth.getInstance().currentUser?.uid!!)

                .addSnapshotListener { snapshot, e ->
                    _articleState.value =
                        snapshot?.toObjects(ArticleItemData::class.java) as List<ArticleItemData>


                }
            delay(1000)
            _isRefreshing.emit(false)
        }
    }


    suspend fun getAuthorName(
        articleItemData: ArticleItemData,
        author: MutableState<String>,
    ) {

        viewModelScope.async {
            firestoreUseCases.getArticleAuthorNameById(
                "user",
                articleItemData.user_id!!
            )
        }.await().addOnCompleteListener {
            document = it.result
            author.value =
                it.result.get("f_name").toString() + " " + it.result.get("l_name").toString()
        }

    }

}