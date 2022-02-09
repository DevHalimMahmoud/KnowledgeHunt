package com.example.knowledgehunt.presentation.screens.mainScreen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.use_case.AuthUseCases
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppMainScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val storageUseCases: StorageUseCases,
    private val firestoreUseCases: FirestoreUseCases,

    ) : ViewModel() {
    private var _profileImageUrl: MutableState<Uri> =
        mutableStateOf(Uri.parse("https://storage.googleapis.com/glaze-ecom.appspot.com/images/P2uSA0D_6/thumbs/232.png"))
    val profileImageUrl: State<Uri> = _profileImageUrl
    var showProfileDialog: MutableState<Boolean> = mutableStateOf(false)

    private var _userName: MutableState<String?> = mutableStateOf("Name")
    val username: State<String?> = _userName

    private var _email: MutableState<String?> = mutableStateOf("email@domain.com")
    val email: State<String?> = _email

    private var _userData: MutableState<DocumentSnapshot?> = mutableStateOf(null)

    init {
        viewModelScope.launch {

        }
    }

    suspend fun loggedIn(): Boolean {
        return authUseCases.getCurrentUser() != null
    }

    fun logoutResults() {
        viewModelScope.launch {
            authUseCases.logout()
        }
    }

    fun getTopBarProfileImage() {
        viewModelScope.launch {
            storageUseCases.getStorageImageUrl(
                "user",
                FirebaseAuth.getInstance().currentUser?.uid!!
            )
                .addOnCompleteListener { task ->
                    _profileImageUrl.value =
                        task.result.normalizeScheme()
                }
        }
    }

    fun getUserName() {
        viewModelScope.launch {

            firestoreUseCases.getCurrentUserData().addOnCompleteListener {
                _userData.value = it.result

                _userName.value =
                    _userData.value?.get("f_name").toString() + " " + _userData.value?.get("l_name")
                        .toString()

                Log.d("TAG", "getUserName: ${_userName.value}")
            }

        }
    }

    fun getUserEmail() {
        viewModelScope.launch {
            _email.value = authUseCases.getCurrentUserEmail()
        }
    }
}