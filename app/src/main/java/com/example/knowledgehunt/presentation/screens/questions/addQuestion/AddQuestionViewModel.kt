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
    var publishError: MutableState<String> = mutableStateOf("Article Published Successfully!")
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


    fun notEmpty(): Boolean {
        return titleState.value.text.isNotBlank() && languageState.value.text.isNotBlank() && contentState.value.text.isNotBlank()
    }

    private fun dataMap(): HashMap<String, Any?> {
        mutableMap["content"] = contentState.value.text
        mutableMap["date"] = java.sql.Timestamp(System.currentTimeMillis())
        mutableMap["description"] = languageState.value.text
        mutableMap["reactions"] = listOf(0, 0, 0, 0, 0, 0)
        mutableMap["title"] = titleState.value.text
        mutableMap["user_id"] = FirebaseAuth.getInstance().currentUser?.uid!!
        return mutableMap
    }

    fun publishQuestion() {
        updateNumberOfQuestions()
        viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            mutableMap = dataMap()
            firestoreUseCases.addArticleDataToFirestore(mutableMap)
                .addOnCompleteListener { task1 ->
                    publishStates.value = true
                    publishError.value =
                        "Article Published Successfully!"
                }
        }
    }

    private fun updateNumberOfQuestions() {
        viewModelScope.launch {
            firestoreUseCases.getNumberOfPublishedArticles().addOnCompleteListener {
                if (it.isSuccessful) {
                    val mutableMap: HashMap<String, Any?> = hashMapOf()
                    mutableMap["num_articles"] = it.result.get("num_articles") as Long + 1
                    mutableMap["score"] = it.result.get("score") as Long + 10
                    viewModelScope.launch {
                        firestoreUseCases.updateNumberOfPublishedArticles(
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

