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
import com.example.knowledgehunt.domain.use_case.UseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppMainScreenViewModel @Inject constructor(
    private val useCases: UseCases

) : ViewModel(), LifecycleObserver {
    var profileImage: MutableState<Bitmap> =
        mutableStateOf(R.drawable.logo_no_text.toDrawable().toBitmap(50, 50))

    suspend fun loggedIn(): Boolean {
        return useCases.getCurrentUser() != null

    }

    suspend fun LogoutResults() {
        useCases.logout()
    }

    suspend fun getTopBarProfileImage() {


        useCases.getStorageImage("user", FirebaseAuth.getInstance().currentUser?.uid.toString())
            .addOnCompleteListener { task ->
                profileImage.value =
                    BitmapFactory.decodeByteArray(task.result, 0, task.result.size)
            }
    }
}