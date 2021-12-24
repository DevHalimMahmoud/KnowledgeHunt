package com.example.knowledgehunt.viewModels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.FirebaseAuthServices

class AppMainScreenViewModel constructor(): ViewModel(),LifecycleObserver {

    suspend fun LogoutResults() {
        FirebaseAuthServices.Logout()
    }
}