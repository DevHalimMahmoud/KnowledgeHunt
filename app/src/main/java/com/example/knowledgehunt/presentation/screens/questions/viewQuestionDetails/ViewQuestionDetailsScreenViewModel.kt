package com.example.knowledgehunt.presentation.screens.questions.viewQuestionDetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.utils.QuestionArguments
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
class ViewQuestionDetailsScreenViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(true)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private var _questionId: MutableState<String?> = mutableStateOf(null)
    val answerState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())

    init {
        refresh()
        getQuestionViewCount()
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

    suspend fun getUserData(
        questionItemData: HashMap<String, Any>?,
        authorData: MutableState<DocumentSnapshot?>,
    ) {
        viewModelScope.async {
            firestoreUseCases.getArticleAuthorNameById(
                "user",
                questionItemData?.get("user_id").toString()
            )
        }.await().addOnCompleteListener {
            authorData.value =
                it.result
        }
    }

    private fun updateQuestionViewCount() {

        val mutableMap: MutableMap<String, Any?> = mutableMapOf()

        if (_questionId.value != null) {
            mutableMap["views"] = (QuestionArguments.instance?.questionItemData?.views as Long) + 1
            viewModelScope.launch {
                firestoreUseCases.updateArticleViewsCount(
                    "questions",
                    _questionId.value!!,
                    mutableMap
                )
            }
        }
    }

    private fun getQuestionViewCount() {
        viewModelScope.launch {
            firestoreUseCases.getArticleViewsCount(
                "questions",
                QuestionArguments.instance?.questionItemData?.user_id.toString(),
                "user_id",
                "date",
                QuestionArguments.instance?.questionItemData?.date
            ).addOnCompleteListener {
                _questionId.value = it.result.documents[0].id
                updateQuestionViewCount()
            }
        }
    }

    fun addAnswer() {
        val mutableMap: HashMap<String, Any>? = HashMap()
        mutableMap?.set("user_id", FirebaseAuth.getInstance().currentUser?.uid.toString())
        mutableMap?.set("answer", answerState.value.text)
        mutableMap?.set("upvotes", 0.toLong())
        mutableMap?.set("downvotes", 0.toLong())
        QuestionArguments.instance?.questionItemData?.answers?.add(mutableMap)

        addToFirestore()
        updateUserStatistics()
        answerState.value = TextFieldValue("")
        refresh()

    }

    private fun addToFirestore() {
        val mutableMap: HashMap<String, Any?> = hashMapOf()
        mutableMap["content"] = QuestionArguments.instance?.questionItemData?.content
        mutableMap["date"] = QuestionArguments.instance?.questionItemData?.date
        mutableMap["prog_language"] = QuestionArguments.instance?.questionItemData?.prog_language
        mutableMap["views"] = QuestionArguments.instance?.questionItemData?.views
        mutableMap["title"] = QuestionArguments.instance?.questionItemData?.title
        mutableMap["user_id"] = QuestionArguments.instance?.questionItemData?.user_id
        mutableMap["question_upvotes"] =
            QuestionArguments.instance?.questionItemData?.question_upvotes
        mutableMap["question_downvotes"] =
            QuestionArguments.instance?.questionItemData?.question_downvotes
        mutableMap["field"] = QuestionArguments.instance?.questionItemData?.field
        mutableMap["answers"] = QuestionArguments.instance?.questionItemData?.answers
        viewModelScope.launch {
            firestoreUseCases.updateArticleViewsCount(
                "questions",
                _questionId.value!!,
                mutableMap
            )
        }
    }

    private fun updateUserStatistics() {
        viewModelScope.launch {
            firestoreUseCases.getUserDataStatistics().addOnCompleteListener {
                if (it.isSuccessful) {
                    val mutableMap: HashMap<String, Any?> = hashMapOf()
                    mutableMap["num_answers"] = it.result.get("num_answers") as Long + 1
                    mutableMap["score"] = it.result.get("score") as Long + 5
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

    fun upVote() {
        QuestionArguments.instance?.questionItemData?.question_upvotes =
            QuestionArguments.instance?.questionItemData?.question_upvotes as Long + 1
        val mutableMap: HashMap<String, Any?> = hashMapOf()
        mutableMap["content"] = QuestionArguments.instance?.questionItemData?.content
        mutableMap["date"] = QuestionArguments.instance?.questionItemData?.date
        mutableMap["prog_language"] = QuestionArguments.instance?.questionItemData?.prog_language
        mutableMap["views"] = QuestionArguments.instance?.questionItemData?.views
        mutableMap["title"] = QuestionArguments.instance?.questionItemData?.title
        mutableMap["user_id"] = QuestionArguments.instance?.questionItemData?.user_id
        mutableMap["question_upvotes"] =
            QuestionArguments.instance?.questionItemData?.question_upvotes as Long
        mutableMap["question_downvotes"] =
            QuestionArguments.instance?.questionItemData?.question_downvotes
        mutableMap["field"] = QuestionArguments.instance?.questionItemData?.field
        mutableMap["answers"] = QuestionArguments.instance?.questionItemData?.answers
        viewModelScope.launch {
            firestoreUseCases.updateArticleViewsCount(
                "questions",
                _questionId.value!!,
                mutableMap
            )
        }
        refresh()
    }

    fun downVote() {
        QuestionArguments.instance?.questionItemData?.question_downvotes =
            QuestionArguments.instance?.questionItemData?.question_downvotes as Long + 1
        val mutableMap: HashMap<String, Any?> = hashMapOf()
        mutableMap["content"] = QuestionArguments.instance?.questionItemData?.content
        mutableMap["date"] = QuestionArguments.instance?.questionItemData?.date
        mutableMap["prog_language"] = QuestionArguments.instance?.questionItemData?.prog_language
        mutableMap["views"] = QuestionArguments.instance?.questionItemData?.views
        mutableMap["title"] = QuestionArguments.instance?.questionItemData?.title
        mutableMap["user_id"] = QuestionArguments.instance?.questionItemData?.user_id
        mutableMap["question_upvotes"] =
            QuestionArguments.instance?.questionItemData?.question_upvotes
        mutableMap["question_downvotes"] =
            QuestionArguments.instance?.questionItemData?.question_downvotes
        mutableMap["field"] = QuestionArguments.instance?.questionItemData?.field
        mutableMap["answers"] = QuestionArguments.instance?.questionItemData?.answers
        viewModelScope.launch {
            firestoreUseCases.updateArticleViewsCount(
                "questions",
                _questionId.value!!,
                mutableMap
            )
        }
        refresh()
    }
}