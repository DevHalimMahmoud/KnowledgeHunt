package com.example.knowledgehunt.presentation.screens.mainScreen

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.domain.use_case.AuthUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppMainScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val storageUseCases: StorageUseCases

) : ViewModel(), LifecycleObserver {
    var profileImageUrl: MutableState<Uri> =
        mutableStateOf(Uri.parse("https://storage.googleapis.com/glaze-ecom.appspot.com/images/P2uSA0D_6/thumbs/232.png"))

    suspend fun loggedIn(): Boolean {
        return authUseCases.getCurrentUser() != null
    }

    suspend fun logoutResults() {
        authUseCases.logout()
    }

    suspend fun getTopBarProfileImage() {
        storageUseCases.getStorageImageUrl("user", FirebaseAuth.getInstance().currentUser?.uid!!)
            .addOnCompleteListener { task ->
                profileImageUrl.value =
                    task.result.normalizeScheme()
            }
    }
}