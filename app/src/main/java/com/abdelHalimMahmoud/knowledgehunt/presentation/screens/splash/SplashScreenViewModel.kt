package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.splash

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.abdelHalimMahmoud.knowledgehunt.domain.use_case.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
@SuppressLint("CustomSplashScreen")
class SplashScreenViewModel @Inject constructor(private val authUseCases: AuthUseCases) :
    ViewModel() {

    val currentUser: FirebaseUser? by lazy { runBlocking { authUseCases.getCurrentUser() } }

}