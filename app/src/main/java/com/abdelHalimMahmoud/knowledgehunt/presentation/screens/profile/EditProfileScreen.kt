package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.profile

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abdelHalimMahmoud.knowledgehunt.domain.models.Screens
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.BackTopBar
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.OutlinedButtonItem
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.PasswordFiledUnit
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.TextFieldUnit
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditProfileScreen(navController: NavHostController) {
    val viewModel: EditProfileViewModel = hiltViewModel()
    val context = LocalContext.current


    ReAuthenticateDialog(viewModel, context)

    Scaffold(

        topBar = {
            BackTopBar(
                title = Screens.EditProfile.title,
                buttonIcon = TablerIcons.ArrowBack,
                modifier = Modifier,
                onClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {

            if (viewModel.updateDataProgressIndicator.value) {
                if (viewModel.notEmpty()) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                            .clickable {
                                viewModel.updateProfileData(context)
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
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 8.dp)
        ) {
            GlideImage(
                viewModel.profileImageUrl.value,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .size(250.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.onSurface, CircleShape),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = MaterialTheme.colors.secondary,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                circularReveal = CircularReveal(500),
            )

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
            OutlinedButtonItem(
                onClick = {
                    if (viewModel.emailState.value.text.isNullOrBlank()) {
                        Toast.makeText(
                            context,
                            "Please Enter A Valid Email",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        viewModel.dialogState.value = true
                    }
                },
                text = "Change Email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .border(1.dp, Color.Red, RoundedCornerShape(16.dp)),
                enableState = remember { mutableStateOf(true) },
            )
            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedButtonItem(
                onClick = {
                    viewModel.resetPassword(context)
                },
                text = "Reset Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp)),
                enableState = remember { mutableStateOf(true) },
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
fun ReAuthenticateDialog(
    viewModel: EditProfileViewModel,
    context: Context
) {
    if (viewModel.dialogState.value) {
        Dialog(
            onDismissRequest = {
                viewModel.dialogState.value = false
            }
        ) {

            Surface(
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.onSurface
            ) {

                Column(Modifier.verticalScroll(rememberScrollState())) {

                    PasswordFiledUnit(
                        hint = "Password",
                        onImeAction = {
                            viewModel.authPasswordErrorState.value =
                                viewModel.authPasswordState.value.text.isEmpty()
                        },
                        modifier = Modifier
                            .padding(8.dp),
                        imeAction = ImeAction.Next,
                        errorState = viewModel.authPasswordErrorState,
                        passwordState = viewModel.authPasswordState,
                        errorText = "Required!",
                        keyboardType = KeyboardType.Password
                    )
                    if (viewModel.updateEmailProgressState.value) {
                        CircularProgressIndicator(
                            Modifier
                                .align(CenterHorizontally)
                                .padding(horizontal = 18.dp, vertical = 8.dp)
                        )
                    } else {
                        OutlinedButtonItem(
                            onClick = {
                                viewModel.reAuthenticate(context)
                            },
                            text = "Verify",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp, vertical = 8.dp)
                                .border(1.dp, Color.Red, RoundedCornerShape(16.dp)),
                            enableState = remember { mutableStateOf(true) },
                        )
                    }
                }
            }
        }
    }
}



