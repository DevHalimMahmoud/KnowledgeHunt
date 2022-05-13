package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.questions.myQuestionDetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.abdelHalimMahmoud.knowledgehunt.domain.use_case.FirestoreUseCases
import com.abdelHalimMahmoud.knowledgehunt.domain.utils.QuestionArguments
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyQuestionDetailsScreenViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {

    val publishArticleProgressIndicator: MutableState<Boolean> = mutableStateOf(true)
    var publishError: MutableState<String> = mutableStateOf("Question Updated Successfully!")
    var publishStates: MutableState<Boolean> = mutableStateOf(false)

    var titleState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue(
            QuestionArguments.instance?.questionItemData?.title ?: "Question Title"
        )
    )
    var titleErrorState: MutableState<Boolean> = mutableStateOf(false)

    var languageState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue(
            QuestionArguments.instance?.questionItemData?.prog_language ?: "Language"
        )
    )
    var languageErrorState: MutableState<Boolean> = mutableStateOf(false)

    var toolState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue(
            QuestionArguments.instance?.questionItemData?.field ?: "Framework"
        )
    )
    var toolErrorState: MutableState<Boolean> = mutableStateOf(false)

    var contentState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue(
            QuestionArguments.instance?.questionItemData?.content ?: "Content"
        )
    )
    private var _deleteDocumentState: MutableState<Boolean> = mutableStateOf(false)
    var deleteDocumentState: State<Boolean> = _deleteDocumentState

    private var _articleId: MutableState<String?> = mutableStateOf(null)

    var contentErrorState: MutableState<Boolean> = mutableStateOf(false)
    var mutableMap: HashMap<String, Any?> = hashMapOf()

    init {
        getArticleId()
    }

    fun deleteArticle(navController: NavController) {

        _deleteDocumentState.value = true
        viewModelScope.launch {
            deleteFirestoreQuestionDocument()
            updateNumberOfAskedQuestions()
            delay(1000)
            navController.popBackStack()
        }
    }

    private fun deleteFirestoreQuestionDocument() {
        viewModelScope.launch {
            firestoreUseCases.deleteQuestionFirestoreDocument(
                "questions",
                _articleId.value.toString()
            ).addOnCompleteListener {
                _deleteDocumentState.value = false
            }
        }
    }

    fun notEmpty() =
        titleState.value.text.isNotBlank() && languageState.value.text.isNotBlank() && contentState.value.text.isNotBlank() && toolState.value.text.isNotBlank()

    private suspend fun dataMap(): HashMap<String, Any?> {
        mutableMap["content"] = contentState.value.text
        mutableMap["filed"] = toolState.value.text
        mutableMap["title"] = titleState.value.text
        mutableMap["prog_language"] = languageState.value.text

        return mutableMap
    }

    fun updateArticle() {

        if (_articleId.value != null) {
            viewModelScope.launch {
                firestoreUseCases.updateQuestionData(
                    "questions",
                    _articleId.value!!,
                    dataMap()
                ).addOnCompleteListener {
                    publishArticleProgressIndicator.value = true
                    publishStates.value = true
                    publishError.value =
                        "Question Updated Successfully!"
                }
            }
        }
    }

    private fun updateNumberOfAskedQuestions() {
        viewModelScope.launch {
            firestoreUseCases.getUserDataStatistics().addOnCompleteListener {
                if (it.isSuccessful) {

                    val mutableMap: HashMap<String, Any?> = hashMapOf()
                    mutableMap["num_ask"] = it.result.get("num_articles") as Long - 1
                    mutableMap["score"] = it.result.get("score") as Long - 5
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
                "questions",
                QuestionArguments.instance?.questionItemData?.user_id.toString(),
                "user_id",
                "date",
                QuestionArguments.instance?.questionItemData?.date
            ).addOnCompleteListener {
                _articleId.value = it.result.documents[0].id

            }
        }
    }
}