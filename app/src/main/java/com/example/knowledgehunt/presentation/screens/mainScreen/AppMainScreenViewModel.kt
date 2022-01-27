package com.example.knowledgehunt.presentation.screens.mainScreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.R
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
    var profileImage: MutableState<Bitmap> =
        mutableStateOf(R.drawable.logo_no_text.toDrawable().toBitmap(5, 5))

    suspend fun loggedIn(): Boolean {
        return authUseCases.getCurrentUser() != null
    }

    suspend fun logoutResults() {
        authUseCases.logout()
    }

    suspend fun getTopBarProfileImage() {
        storageUseCases.getStorageImage("user", FirebaseAuth.getInstance().currentUser?.uid!!)
            .addOnCompleteListener { task ->
                profileImage.value =
                    BitmapFactory.decodeByteArray(task.result, 0, task.result.size)
            }
    }
}