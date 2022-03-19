package com.example.knowledgehunt.presentation.screens.mcq.viewMCQ

import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.domain.use_case.AuthUseCases
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewMCQScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val storageUseCases: StorageUseCases,
    private val firestoreUseCases: FirestoreUseCases,

    ) : ViewModel() {
}