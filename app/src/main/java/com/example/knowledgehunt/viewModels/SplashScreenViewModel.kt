package com.example.knowledgehunt.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.FirebaseAuthServices.getCurrentUser
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.scopes.ViewModelScoped

@SuppressLint("CustomSplashScreen")
class SplashScreenViewModel() : ViewModel() {

    val currentUser : FirebaseUser? by lazy { getCurrentUser() }

}