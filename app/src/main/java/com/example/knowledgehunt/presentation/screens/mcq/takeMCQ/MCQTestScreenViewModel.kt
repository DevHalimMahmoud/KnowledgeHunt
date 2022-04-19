package com.example.knowledgehunt.presentation.screens.mcq.takeMCQ

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import com.example.knowledgehunt.domain.utils.MCQTestArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MCQTestScreenViewModel @Inject constructor(
    private val storageUseCases: StorageUseCases,
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {
    var timeLeft: MutableState<Float> = mutableStateOf(0.0f)
    var score: MutableState<Int> = mutableStateOf(0)
    var answeredQuestions = mutableStateOf(0)
    val questions =
        MCQTestArguments.instance?.itemData?.questions?.shuffled()?.toList()?.subList(0, 10)
            ?.toMutableList()

    init {

    }

}