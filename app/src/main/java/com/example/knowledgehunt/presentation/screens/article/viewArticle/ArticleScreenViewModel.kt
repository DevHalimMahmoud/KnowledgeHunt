package com.example.knowledgehunt.presentation.screens.article.viewArticle

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.models.ArticleItemData
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleScreenViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)

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
            // A fake 2 second 'refresh'
            _isRefreshing.emit(true)
            delay(2000)
            _isRefreshing.emit(false)
        }
    }

    fun getArticles() {
        viewModelScope.launch {
            firestoreUseCases.getCollection("articles").addSnapshotListener { snapshot, e ->
                _articleState.value =
                    snapshot?.toObjects(ArticleItemData::class.java) as List<ArticleItemData>
            }
        }
    }

}