package com.example.knowledgehunt.domain.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.knowledgehunt.domain.models.QuestionItemData
import com.google.firebase.firestore.DocumentSnapshot

// This is NOT the best way to pass args when navigating from composable to another
// HOWEVER due to the limitation of the recommended way i was forced to do this may god forgive me
class QuestionArguments private constructor() {
    var questionItemData: QuestionItemData? = null
    var authorData: MutableState<DocumentSnapshot?> = mutableStateOf(null)

    fun destroy() {
        questionItemData = null
        authorData = mutableStateOf(null)
        instance = null
    }

    companion object {
        var instance: QuestionArguments? = null
            get() {
                if (field == null) {
                    field = QuestionArguments()
                }
                return field
            }
            private set
    }
}