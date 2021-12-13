package com.example.knowledgehunt.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.FirebaseAuthServices.login
import com.example.knowledgehunt.services.FirebaseAuthServices.resetPassword
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.runBlocking


class LoginScreenViewModel constructor(
    var dialogEmailState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var dialogEmailErrorState: MutableState<Boolean> = mutableStateOf(false),
    var focusRequester: FocusRequester = FocusRequester(),
    var emailState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var emailErrorState: MutableState<Boolean> = mutableStateOf(false),
    var passwordState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var passwordErrorState: MutableState<Boolean> = mutableStateOf(false),
    var loginButtonState: MutableState<Boolean> = mutableStateOf(true),
    val openDialog: MutableState<Boolean> = mutableStateOf(false)
) : ViewModel(), LifecycleObserver {

    fun loginResults(
    ): Task<AuthResult> {
        return runBlocking {
            login(
                emailState.value.text,
                password = passwordState.value.text
            )
        }
    }

    fun resetPasswordResults(
    ): Task<Void> {
        return runBlocking {
            resetPassword(dialogEmailState.value.text)
        }
    }

}


