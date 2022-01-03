package com.example.knowledgehunt.presentation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.data.repository.FirebaseAuthServices.login
import com.example.knowledgehunt.data.repository.FirebaseAuthServices.resetPassword
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult


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
) : ViewModel() {

    suspend fun loginResults(
    ): Task<AuthResult> {
        return login(
            emailState.value.text,
            password = passwordState.value.text
        )
    }

    suspend fun resetPasswordResults(
    ): Task<Void> {
        return resetPassword(dialogEmailState.value.text)
    }

}


