package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelHalimMahmoud.knowledgehunt.domain.models.ArticleItemData
import com.abdelHalimMahmoud.knowledgehunt.domain.models.MCQItemData
import com.abdelHalimMahmoud.knowledgehunt.domain.models.QuestionItemData
import com.abdelHalimMahmoud.knowledgehunt.domain.use_case.FirestoreUseCases
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
) : ViewModel() {
    private val _articleState = mutableStateOf<List<ArticleItemData>>(listOf())
    val articleState: State<List<ArticleItemData>> = _articleState
    private val _questionState = mutableStateOf<List<QuestionItemData>>(listOf())
    val questionState: State<List<QuestionItemData>> = _questionState
    private val _MCQState = mutableStateOf<List<MCQItemData>>(listOf())
    val MCQState: State<List<MCQItemData>> = _MCQState
    var showDialog: MutableState<Boolean> = mutableStateOf(false)
    val query = mutableStateOf("")

    init {
        getArticles()
        getQuestions()
        getMCQTests()
    }

    fun onQueryChanged(word: String) {
        query.value = word
        getArticles()
        getQuestions()
        getMCQTests()
    }

    private fun getArticles() {
        viewModelScope.launch {
            firestoreUseCases.getArticles("articles")
                .whereGreaterThanOrEqualTo("title", query.value.trim())
                .addSnapshotListener { snapshot, e ->
                    if (snapshot != null) {
                        _articleState.value =
                            snapshot.toObjects(ArticleItemData::class.java) as List<ArticleItemData>
                    }

                }
        }
    }

    private fun getQuestions() {
        viewModelScope.launch {

            firestoreUseCases.getQuestions("questions")
                .whereGreaterThanOrEqualTo("title", query.value.trim())
                .addSnapshotListener { snapshot, e ->
                    if (snapshot != null) {
                        _questionState.value =
                            (snapshot.toObjects(QuestionItemData::class.java) as List<QuestionItemData>)
                    }
                }
        }
    }

    private fun getMCQTests() {
        viewModelScope.launch {
            firestoreUseCases.getMCQTests("mcq")
                .whereGreaterThanOrEqualTo("title", query.value.trim())
                .addSnapshotListener { snapshot, e ->
                    if (snapshot != null) {
                        _MCQState.value =
                            snapshot.toObjects(MCQItemData::class.java) as List<MCQItemData>
                    }
                }
        }
    }

    suspend fun getAuthorName(
        id: String,
        authorData: MutableState<DocumentSnapshot?>,
    ) {
        viewModelScope.async {
            firestoreUseCases.getArticleAuthorNameById(
                "user",
                id
            )
        }.await().addOnCompleteListener {
            authorData.value =
                it.result
        }
    }

}