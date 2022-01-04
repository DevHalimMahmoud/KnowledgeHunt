package com.example.knowledgehunt.presentation.screens.splash

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.domain.use_case.UseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
@SuppressLint("CustomSplashScreen")
class SplashScreenViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    val currentUser: FirebaseUser? by lazy { runBlocking { useCases.getCurrentUser() } }

}