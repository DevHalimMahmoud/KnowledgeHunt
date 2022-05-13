package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.questions.viewQuestions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelHalimMahmoud.knowledgehunt.domain.models.QuestionItemData
import com.abdelHalimMahmoud.knowledgehunt.domain.use_case.FirestoreUseCases
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ViewQuestionsViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(true)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _questionState = mutableStateOf<List<QuestionItemData>>(listOf())
    val questionState: State<List<QuestionItemData>> = _questionState

    init {
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {

            firestoreUseCases.getQuestions("questions").orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, e ->
                    _questionState.value =
                        (snapshot?.toObjects(QuestionItemData::class.java) as List<QuestionItemData>)


                }
            delay(1000)
            _isRefreshing.emit(false)
        }
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

    suspend fun getAuthorName(
        questionItemData: QuestionItemData,
        authorData: MutableState<DocumentSnapshot?>,
    ) {
        viewModelScope.async {
            firestoreUseCases.getArticleAuthorNameById(
                "user",
                questionItemData.user_id!!
            )
        }.await().addOnCompleteListener {
            authorData.value =
                it.result
        }
    }
}