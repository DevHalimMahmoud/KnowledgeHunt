package com.example.knowledgehunt.presentation.screens.splash

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.data.repository.FirebaseAuthServices.getCurrentUser
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.runBlocking

@SuppressLint("CustomSplashScreen")
class SplashScreenViewModel : ViewModel() {

    val currentUser: FirebaseUser? by lazy { runBlocking { getCurrentUser() } }

}