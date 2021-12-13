package com.example.knowledgehunt.viewModels

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

class RegisterScreenViewModel(
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

    var imageUri: MutableState<Uri?> = mutableStateOf<Uri?>(null),
    val bitmap: MutableState<Bitmap?> = mutableStateOf<Bitmap?>(null),

    var floatingActionButtonState: MutableState<Boolean> = mutableStateOf(true),
) : ViewModel(), LifecycleObserver