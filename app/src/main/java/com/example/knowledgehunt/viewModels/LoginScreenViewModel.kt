package com.example.knowledgehunt.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.FirebaseAuthServices.login
import com.example.knowledgehunt.services.FirebaseAuthServices.resetPassword
import com.google.android.datatransport.runtime.dagger.Provides
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel(), LifecycleObserver {
    val openDialog = mutableStateOf(false)

    var dialogEmailState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue()
    )

    var dialogEmailErrorState: MutableState<Boolean> = mutableStateOf(
        false
    )

    var focusRequester: FocusRequester = FocusRequester()

    var emailState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue()
    )

    var emailErrorState: MutableState<Boolean> = mutableStateOf(
        false
    )

    var passwordState: MutableState<TextFieldValue> = mutableStateOf(
        TextFieldValue()
    )
    var passwordErrorState: MutableState<Boolean> = mutableStateOf(
        false
    )
    var loginButtonState: MutableState<Boolean> = mutableStateOf(
        true
    )

    @Provides
    @Singleton
    fun loginResults(
    ): Task<AuthResult> {
        return runBlocking {
            login(

                emailState.value.text,
                password = passwordState.value.text
            )
        }
    }

    @Provides
    @Singleton
    fun resetPasswordResults(
    ): Task<Void> {
        return runBlocking {
            resetPassword(dialogEmailState.value.text)
        }
    }

}


