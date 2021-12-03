package com.example.knowledgehunt.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.getCurrentUser
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.scopes.ViewModelScoped

@SuppressLint("CustomSplashScreen")
@ViewModelScoped
class SplashScreenViewModel() : ViewModel() {

    val currentUser : FirebaseUser? by lazy { getCurrentUser() }

}