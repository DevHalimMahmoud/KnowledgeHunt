package com.example.knowledgehunt.presentation.screens.profile

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.domain.use_case.AuthUseCases
import com.example.knowledgehunt.domain.use_case.FirestoreUseCases
import com.example.knowledgehunt.domain.use_case.ImageUseCases
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditPofileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val storageUseCases: StorageUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    private val imageUseCases: ImageUseCases

) : ViewModel() {
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
        mutableMap["user_token"] = ""
        return mutableMap
    }

}