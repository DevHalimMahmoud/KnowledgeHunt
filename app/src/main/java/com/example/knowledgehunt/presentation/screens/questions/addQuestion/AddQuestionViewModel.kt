package com.example.knowledgehunt.presentation.screens.questions.addQuestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQuestionViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {

    val publishArticleProgressIndicator: MutableState<Boolean> = mutableStateOf(true)
    var publishError: MutableState<String> = mutableStateOf("Question Published Successfully!")
    var publishStates: MutableState<Boolean> = mutableStateOf(false)

    var titleState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var titleErrorState: MutableState<Boolean> = mutableStateOf(false)

    var languageState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var languageErrorState: MutableState<Boolean> = mutableStateOf(false)

    var toolState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var toolErrorState: MutableState<Boolean> = mutableStateOf(false)

    var contentState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var contentErrorState: MutableState<Boolean> = mutableStateOf(false)
    var mutableMap: HashMap<String, Any?> = hashMapOf()

    fun notEmpty() = titleState.value.text.isNotBlank() && languageState.value.text.isNotBlank() && contentState.value.text.isNotBlank() && toolState.value.text.isNotBlank()


    private suspend fun dataMap(): HashMap<String, Any?> {
        mutableMap["content"] = contentState.value.text
        mutableMap["date"] = java.sql.Timestamp(System.currentTimeMillis())
        mutableMap["prog_language"] = languageState.value.text
        mutableMap["views"] = 0
        mutableMap["title"] = titleState.value.text
        mutableMap["user_id"] = FirebaseAuth.getInstance().currentUser?.uid!!
        mutableMap["question_upvotes"] = 0
        mutableMap["question_downvotes"] = 0
        mutableMap["field"] = toolState.value.text
        mutableMap["answers"] = listOf<Any>()
        return mutableMap
    }

    fun publishQuestion() {
        updateNumberOfQuestions()
        viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            firestoreUseCases.addQuestion("questions", dataMap())
                .addOnCompleteListener { task1 ->
                    publishStates.value = true
                    publishError.value =
                        "Question Published Successfully!"
                }
        }
    }

    private fun updateNumberOfQuestions() {
        viewModelScope.launch {
            firestoreUseCases.getUserDataStatistics().addOnCompleteListener {
                if (it.isSuccessful) {
                    val mutableMap: HashMap<String, Any?> = hashMapOf()
                    mutableMap["num_ask"] = it.result.get("num_ask") as Long + 1
                    mutableMap["score"] = it.result.get("score") as Long + 5
                    viewModelScope.launch {
                        firestoreUseCases.updateUserDataStatistics(
                            "user",
                            FirebaseAuth.getInstance().currentUser?.uid.toString(),
                            mutableMap
                        ).addOnCompleteListener {
                            publishArticleProgressIndicator.value = true
                        }
                    }
                }
            }
        }
    }
}

