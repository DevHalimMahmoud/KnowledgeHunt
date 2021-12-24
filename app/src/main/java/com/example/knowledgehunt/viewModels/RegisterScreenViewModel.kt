package com.example.knowledgehunt.viewModels

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.ImageServices


class RegisterScreenViewModel(
    var emailState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var emailErrorState: MutableState<Boolean> = mutableStateOf(false),

    var passwordState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var passwordErrorState: MutableState<Boolean> = mutableStateOf(false),

    var firstNameState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var firstNameErrorState: MutableState<Boolean> = mutableStateOf(false),

    var lastNameState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var lastNameErrorState: MutableState<Boolean> = mutableStateOf(false),

    var userNameState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var userNameErrorState: MutableState<Boolean> = mutableStateOf(false),

    var phoneState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var phoneErrorState: MutableState<Boolean> = mutableStateOf(false),

    var ageState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    var ageErrorState: MutableState<Boolean> = mutableStateOf(false),

    var genderExpanded: MutableState<Boolean> = mutableStateOf(false),
    val genderItems: List<String> = listOf("Male", "Female", "Other"),
    var genderSelectedIndex: MutableState<Int> = mutableStateOf(0),

    val imageUri: MutableState<Uri?>? = mutableStateOf(null),

    val bitmap: MutableState<Bitmap?> = mutableStateOf(null),

    var floatingActionButtonState: MutableState<Boolean> = mutableStateOf(true),

    val ImageCompressionProgressIndicator: MutableState<Boolean> = mutableStateOf(false),
    val SignupProgressIndicator: MutableState<Boolean> = mutableStateOf(true)


) : ViewModel(), LifecycleObserver {
    suspend fun compressProfileImage(context: Context) {
        bitmap.value = ImageServices.compressImage(context = context, imageUri)
    }
}