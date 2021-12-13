package com.example.knowledgehunt.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.FirebaseAuthServices.getCurrentUser
import com.google.firebase.auth.FirebaseUser

@SuppressLint("CustomSplashScreen")
class SplashScreenViewModel : ViewModel(), LifecycleObserver {

    val currentUser: FirebaseUser? by lazy { getCurrentUser() }

}