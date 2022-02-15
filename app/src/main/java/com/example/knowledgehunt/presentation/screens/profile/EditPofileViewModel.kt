package com.example.knowledgehunt.presentation.screens.profile

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.use_case.AuthUseCases
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPofileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val storageUseCases: StorageUseCases,
    private val firestoreUseCases: FirestoreUseCases,
) : ViewModel() {
    val dialogState: MutableState<Boolean> = mutableStateOf(false)
    val updateEmailProgressState: MutableState<Boolean> = mutableStateOf(false)

    var authPasswordState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var authPasswordErrorState: MutableState<Boolean> = mutableStateOf(false)

    var emailState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var emailErrorState: MutableState<Boolean> = mutableStateOf(false)

    var firstNameState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var firstNameErrorState: MutableState<Boolean> = mutableStateOf(false)

    var lastNameState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var lastNameErrorState: MutableState<Boolean> = mutableStateOf(false)

    var userNameState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var userNameErrorState: MutableState<Boolean> = mutableStateOf(false)

    var phoneState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var phoneErrorState: MutableState<Boolean> = mutableStateOf(false)

    var ageState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    var ageErrorState: MutableState<Boolean> = mutableStateOf(false)

    var genderExpanded: MutableState<Boolean> = mutableStateOf(false)
    val genderItems: List<String> = listOf("Male", "Female", "Other")
    var genderSelectedIndex: MutableState<Int> = mutableStateOf(0)

    val imageUri: MutableState<Uri?> = mutableStateOf(null)

    val signupProgressIndicator: MutableState<Boolean> = mutableStateOf(true)
    var signupError: MutableState<String> =
        mutableStateOf("User Created Successfully please verify your email")
    var signupStates: MutableState<Boolean> = mutableStateOf(false)

    var mutableMap: HashMap<String, Any?> = hashMapOf()

    fun notEmpty(): Boolean {
        return firstNameState.value.text.isNotBlank() &&
                lastNameState.value.text.isNotBlank() && userNameState.value.text.isNotBlank() && phoneState.value.text.isNotBlank() &&
                ageState.value.text.isNotBlank()
    }

    private fun dataMap(): HashMap<String, Any?> {
        mutableMap["age"] = ageState.value.text
        mutableMap["f_name"] = firstNameState.value.text
        mutableMap["l_name"] = lastNameState.value.text
        mutableMap["phone_num"] = phoneState.value.text
        mutableMap["user_name"] = userNameState.value.text
        return mutableMap
    }

    fun reAuthenticate(context: Context) {
        if (authPasswordState.value.text.isNullOrBlank()) {
            Toast.makeText(
                context,
                "Please Enter A Valid password",
                Toast.LENGTH_LONG
            ).show()
        } else {
            updateEmailProgressState.value = true
            viewModelScope.launch {
                authUseCases.reAuthenticateCurrentUser(authPasswordState.value.text)
                    ?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            updateEmail(context)
                        } else {
                            Toast.makeText(
                                context,
                                it.exception?.localizedMessage,
                                Toast.LENGTH_LONG
                            ).show()
                            updateEmailProgressState.value = false
                        }
                    }
            }
        }
    }

    private fun updateEmail(context: Context) {

        viewModelScope.launch {
            authUseCases.updateCurrentUserEmail(emailState.value.text.trim())
                ?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewModelScope.launch {
                            authUseCases.sendEmailVerification().addOnCompleteListener {
                                Toast.makeText(
                                    context,
                                    "Email Updated Please Verify your email",
                                    Toast.LENGTH_LONG
                                ).show()
                                dialogState.value = false
                                updateEmailProgressState.value = false
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            it.exception?.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                        dialogState.value = false
                        updateEmailProgressState.value = false
                    }
                }
        }
    }
}