package com.example.knowledgehunt.ui

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.*
import com.example.knowledgehunt.viewModels.RegisterScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(navController: NavHostController) {
    val viewModel: RegisterScreenViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    Scaffold(
        topBar = {
            BackTopBar(
                title = Screens.Register.title,
                buttonIcon = Icons.Default.ArrowBack,
                modifier = Modifier,
                onClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {

            if (viewModel.SignupProgressIndicator.value) {
                if (viewModel.notEmpty()) {
                    Image(
                        Icons.Rounded.Check,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                            .clickable {
                                viewModel.SignupProgressIndicator.value = false
                                coroutineScope
                                    .launch(Dispatchers.Unconfined, CoroutineStart.DEFAULT) {
                                        viewModel
                                            .signupNewUser()
                                            .addOnCompleteListener {
                                                coroutineScope
                                                    .launch(
                                                        Dispatchers.Main,
                                                        CoroutineStart.DEFAULT
                                                    ) {
                                                        Toast
                                                            .makeText(
                                                                context,
                                                                viewModel.SignupError,
                                                                Toast.LENGTH_LONG
                                                            )
                                                            .show()
                                                        navController.popBackStack()
                                                    }

                                            }
                                    }


                            },
                    )
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                )
            }
        },

        isFloatingActionButtonDocked = false
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            RequestContentPermission(viewModel, coroutineScope, context)

            TextFieldUnit(
                hint = "Email",
                onImeAction = {
                    viewModel.emailErrorState.value =
                        viewModel.emailState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.emailErrorState,
                textState = viewModel.emailState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text
            )
            PasswordFiledUnit(
                hint = "Password",
                onImeAction = {
                    viewModel.passwordErrorState.value =
                        viewModel.passwordState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.passwordErrorState,
                passwordState = viewModel.passwordState,
                errorText = "Required!",
                keyboardType = KeyboardType.Password
            )
            Row {

                TextFieldUnit(
                    hint = "First Name",
                    onImeAction = {
                        viewModel.firstNameErrorState.value =
                            viewModel.firstNameState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    imeAction = ImeAction.Next,
                    errorState = viewModel.firstNameErrorState,
                    textState = viewModel.firstNameState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Text
                )
                TextFieldUnit(
                    hint = "Last Name",
                    onImeAction = {
                        viewModel.lastNameErrorState.value =
                            viewModel.lastNameState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    imeAction = ImeAction.Next,
                    errorState = viewModel.lastNameErrorState,
                    textState = viewModel.lastNameState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Text
                )
            }
            TextFieldUnit(
                hint = "User Name",
                onImeAction = {
                    viewModel.userNameErrorState.value =
                        viewModel.userNameState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.userNameErrorState,
                textState = viewModel.userNameState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text
            )
            TextFieldUnit(
                hint = "Phone",
                onImeAction = {
                    viewModel.phoneErrorState.value =
                        viewModel.phoneState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.phoneErrorState,
                textState = viewModel.phoneState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Phone
            )
            Row(modifier = Modifier.wrapContentSize(Center)) {
                TextFieldUnit(
                    hint = "Age",
                    onImeAction = {
                        viewModel.ageErrorState.value =
                            viewModel.ageState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.5f),
                    imeAction = ImeAction.Next,
                    errorState = viewModel.ageErrorState,
                    textState = viewModel.ageState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Number
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .weight(0.5f)
                ) {
                    OutlinedButton(
                        onClick = { viewModel.genderExpanded.value = true },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color.Gray),
                    ) {
                        Text(
                            text = "Gender: " + viewModel.genderItems[viewModel.genderSelectedIndex.value] + "🔻",
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.DarkGray
                        )
                    }
                    DropdownMenu(
                        expanded = viewModel.genderExpanded.value,
                        onDismissRequest = { viewModel.genderExpanded.value = false },
                        modifier = Modifier
                    ) {
                        viewModel.genderItems.forEachIndexed { index, s ->
                            DropdownMenuItem(onClick = {
                                viewModel.genderSelectedIndex.value = index
                                viewModel.genderExpanded.value = false
                            }) {

                                Text(text = s)
                            }
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun RequestContentPermission(
    viewModel: RegisterScreenViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {

    val launcher: ManagedActivityResultLauncher<String, Uri?> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            viewModel.imageUri?.value = uri

            if (viewModel.imageUri?.value != null) {
                viewModel.ImageCompressionProgressIndicator.value = true
                Toast.makeText(
                    context,
                    "Compressing Image...",
                    Toast.LENGTH_SHORT
                ).show()
                handelImage(viewModel = viewModel, coroutineScope, context)
            }

        }

    Column(Modifier.fillMaxWidth()) {
        if (viewModel.bitmap.value == null) {
            Image(
                Icons.Default.PersonOutline,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .size(250.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
        } else {
            Image(
                bitmap = viewModel.bitmap.value!!.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .size(250.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.primary, CircleShape)
            )
        }
        if (viewModel.ImageCompressionProgressIndicator.value) {

            Box(
                contentAlignment = Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Transparent, shape = RoundedCornerShape(8.dp))
                    .align(CenterHorizontally)
            ) {
                CircularProgressIndicator()
            }

        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedButtonItem(
        onClick = {

            launcher.launch("image/*")
        },
        text = "Pick a Profile Image",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        enableState = remember { mutableStateOf(true) },
    )
}

fun handelImage(
    viewModel: RegisterScreenViewModel,
    coroutineScope: CoroutineScope,
    context: Context,
) {
    coroutineScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT) {
        viewModel.compressProfileImage(context = context)

    }.invokeOnCompletion {
        viewModel.ImageCompressionProgressIndicator.value = false
    }
}

