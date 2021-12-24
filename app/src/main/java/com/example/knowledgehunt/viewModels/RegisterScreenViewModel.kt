package com.example.knowledgehunt.viewModels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import java.io.ByteArrayOutputStream


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

    val imageUri: MutableState<Uri?>? = mutableStateOf(null),

    val bitmap: MutableState<Bitmap?> = mutableStateOf(null),

    var floatingActionButtonState: MutableState<Boolean> = mutableStateOf(true),

    val loadingDismissRequest: MutableState<Boolean> = mutableStateOf(false)

) : ViewModel(), LifecycleObserver {
    suspend fun compressImage(context: Context) {
        val tempBitmap: Bitmap = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images
                .Media.getBitmap(context.contentResolver, imageUri?.value)

        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver, imageUri?.value!!)
            ImageDecoder.decodeBitmap(source)
        }
        val ostream = ByteArrayOutputStream()

        tempBitmap.compress(Bitmap.CompressFormat.PNG, 75, ostream)
        bitmap.value =
            BitmapFactory.decodeByteArray(ostream.toByteArray(), 0, ostream.toByteArray().size);
    }

}