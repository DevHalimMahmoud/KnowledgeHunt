package com.example.knowledgehunt.viewModels

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.knowledgehunt.services.FirebaseAuthServices
import com.example.knowledgehunt.services.FirebaseFirestoreServices
import com.example.knowledgehunt.services.FirebaseStorageServices.uploadProfileImage
import com.example.knowledgehunt.services.ImageServices
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.runBlocking


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

    val ImageCompressionProgressIndicator: MutableState<Boolean> = mutableStateOf(false),

    val SignupProgressIndicator: MutableState<Boolean> = mutableStateOf(true),
    var SignupError: String = "User Created Successfully please verify your email",

    var mutableMap: HashMap<String, Any?> = hashMapOf()

) : ViewModel() {

    suspend fun compressProfileImage(context: Context) {
        bitmap.value = ImageServices.compressImage(context = context, imageUri)
    }

    suspend fun signupNewUser(): Task<AuthResult> {

        return FirebaseAuthServices.createUserWithEmailAndPassword(
            emailState.value.text,
            passwordState.value.text
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                runBlocking {
                    FirebaseAuthServices.sendEmailVerification()

                    uploadProfileImage(bitmap.value!!).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            dataMap()
                            runBlocking {
                                FirebaseFirestoreServices.addUserDataToFirestore(mutableMap)
                                    .addOnCompleteListener { task ->
                                        SignupError = if (task.isSuccessful) {
                                            "User Created Successfully please verify your email"
                                        } else {
                                            task.exception?.localizedMessage.toString()
                                        }
                                    }
                            }
                        } else {
                            SignupError = task.exception?.localizedMessage.toString()
                        }
                    }
                }
            } else {
                SignupError = task.exception?.localizedMessage.toString()

            }
            SignupProgressIndicator.value = true

        }
    }

    fun notEmpty(): Boolean {
        return emailState.value.text.isNotBlank() && passwordState.value.text.isNotBlank() && firstNameState.value.text.isNotBlank() &&
                lastNameState.value.text.isNotBlank() && userNameState.value.text.isNotBlank() && phoneState.value.text.isNotBlank() &&
                ageState.value.text.isNotBlank() && bitmap.value != null
    }

    private fun dataMap() {
        mutableMap["age"] = ageState.value.text
        mutableMap["f_name"] = firstNameState.value.text
        mutableMap["l_name"] = lastNameState.value.text
        mutableMap["level"] = 0
        mutableMap["num_answers"] = 0
        mutableMap["num_articles"] = 0
        mutableMap["num_ask"] = 0
        mutableMap["num_contests"] = 0
        mutableMap["num_downvote"] = 0
        mutableMap["num_mcq"] = 0
        mutableMap["phone_num"] = phoneState.value.text
        mutableMap["score"] = 0
        mutableMap["user_name"] = userNameState.value.text
        mutableMap["user_token"] = ""


    }

}