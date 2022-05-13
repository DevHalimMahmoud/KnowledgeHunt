package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.abdelHalimMahmoud.knowledgehunt.domain.use_case.AuthUseCases
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    var dialogEmailState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var dialogEmailErrorState: MutableState<Boolean> = mutableStateOf(false)
    var focusRequester: FocusRequester = FocusRequester()
    var emailState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var emailErrorState: MutableState<Boolean> = mutableStateOf(false)
    var passwordState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var passwordErrorState: MutableState<Boolean> = mutableStateOf(false)
    var loginButtonState: MutableState<Boolean> = mutableStateOf(true)
    val openDialog: MutableState<Boolean> = mutableStateOf(false)

    suspend fun loginResults(
    ): Task<AuthResult> {
        return authUseCases.login(
            emailState.value.text.trim(),
            password = passwordState.value.text
        )
    }

    suspend fun resetPasswordResults(
    ): Task<Void> {
        return authUseCases.resetPassword(dialogEmailState.value.text.trim())
    }

}


