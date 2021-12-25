package com.example.knowledgehunt.viewModels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.R
import com.example.knowledgehunt.services.FirebaseAuthServices
import com.example.knowledgehunt.services.FirebaseStorageServices

class AppMainScreenViewModel constructor(
    var profileImage: MutableState<Bitmap> =
        mutableStateOf(R.drawable.logo_no_text.toDrawable().toBitmap(50, 50))
) : ViewModel(), LifecycleObserver {


    //    init {
//        viewModelScope.launch {
//            if (FirebaseAuthServices.getCurrentUser() != null) {
//                getTopBarProfileImage()
//            }
//        }
//    }
    suspend fun loggedIn(): Boolean {
        return FirebaseAuthServices.getCurrentUser() != null

    }

    suspend fun LogoutResults() {
        FirebaseAuthServices.Logout()
    }

    suspend fun getTopBarProfileImage() {


        FirebaseStorageServices.getProfileImage().addOnCompleteListener { task ->
            profileImage.value =
                BitmapFactory.decodeByteArray(task.result, 0, task.result.size)
        }
    }
}