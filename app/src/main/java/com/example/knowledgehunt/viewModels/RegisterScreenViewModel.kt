package com.example.knowledgehunt.viewModels

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.services.FirebaseAuthServices
import com.example.knowledgehunt.services.FirebaseFirestoreServices
import com.example.knowledgehunt.services.FirebaseStorageServices.uploadProfileImage
import com.example.knowledgehunt.services.ImageServices
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


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
    var SignupError: MutableState<String> = mutableStateOf("User Created Successfully please verify your email"),
    var SigninStates: MutableState<Boolean> = mutableStateOf(false),

    var mutableMap: HashMap<String, Any?> = hashMapOf()

) : ViewModel() {

    suspend fun compressProfileImage(context: Context) {
        bitmap.value = ImageServices.compressImage(context = context, imageUri)
    }

    suspend fun signupNewUser(): Task<AuthResult> {


        return viewModelScope.async(
            Dispatchers.IO,
            CoroutineStart.DEFAULT
        ) { addEmailAndPassword() }
            .await()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModelScope.launch(
                        Dispatchers.IO, CoroutineStart.DEFAULT
                    ) {
                        FirebaseAuthServices.sendEmailVerification()
                    }
                    viewModelScope.launch(
                        Dispatchers.Default,
                        CoroutineStart.DEFAULT
                    ) {
                        uploadImageToStorage().addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                viewModelScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
                                    addDataToFireStore().addOnCompleteListener { task ->

                                        if (task.isSuccessful) {
                                            viewModelScope.launch(
                                                Dispatchers.IO,
                                                CoroutineStart.DEFAULT
                                            ) {
                                                SignupProgressIndicator.value = true
                                                FirebaseAuthServices.Logout()
                                                SignupError.value =
                                                    "User Created Successfully please verify your email"
                                                SigninStates.value = true
                                            }

                                        } else {
                                            SignupError.value =
                                                task.exception?.localizedMessage.toString()
                                            SignupProgressIndicator.value = true
                                            SigninStates.value = true

                                        }
                                    }
                                }
                            } else {
                                SignupError.value = task.exception?.localizedMessage.toString()
                                SignupProgressIndicator.value = true
                                SigninStates.value = true

                            }
                        }
                    }
                } else {
                    SignupError.value = task.exception?.localizedMessage.toString()
                    SignupProgressIndicator.value = true
                    SigninStates.value = true
                }
            }
    }

    private suspend fun addEmailAndPassword(): Task<AuthResult> {
        return FirebaseAuthServices.createUserWithEmailAndPassword(
            emailState.value.text,
            passwordState.value.text
        )


    }

    private suspend fun uploadImageToStorage(): StorageTask<UploadTask.TaskSnapshot> {

        return uploadProfileImage(
            bitmap.value!!,
            "user",
            FirebaseAuth.getInstance().currentUser?.uid.toString()
        )

    }

    private suspend fun addDataToFireStore(): Task<Void> {

        return FirebaseFirestoreServices.addUserDataToFirestore(dataMap())


    }

    fun notEmpty(): Boolean {
        return emailState.value.text.isNotBlank() && passwordState.value.text.isNotBlank() && firstNameState.value.text.isNotBlank() &&
                lastNameState.value.text.isNotBlank() && userNameState.value.text.isNotBlank() && phoneState.value.text.isNotBlank() &&
                ageState.value.text.isNotBlank() && bitmap.value != null
    }

    private fun dataMap(): HashMap<String, Any?> {
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

        return mutableMap
    }

}