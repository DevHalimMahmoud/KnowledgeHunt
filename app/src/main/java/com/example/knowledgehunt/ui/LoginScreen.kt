package com.example.knowledgehunt.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.ui.components.*
import com.example.knowledgehunt.viewModels.LoginScreenViewModel
import com.google.android.datatransport.runtime.dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel: LoginScreenViewModel = viewModel()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        drawerGesturesEnabled = false
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_with_title),
                contentDescription = null, // decorative element
                Modifier
                    .align(CenterHorizontally),

                )
            TextFieldUnit(
                hint = "Email",
                onImeAction = {
                    viewModel.emailErrorState.value = viewModel.emailState.value.text.isEmpty()
                    viewModel.focusRequester.requestFocus()
                },
                modifier = Modifier,
                imeAction = ImeAction.Next,
                errorState = viewModel.emailErrorState,
                textState = viewModel.emailState,
                errorText = "please enter a valid email"
            )
            Password(
                hint = "password",
                onImeAction = {
                    viewModel.passwordErrorState.value =
                        viewModel.passwordState.value.text.isEmpty()
                    viewModel.focusRequester.freeFocus()
                },
                modifier = Modifier.focusRequester(viewModel.focusRequester),
                errorState = viewModel.passwordErrorState,
                passwordState = viewModel.passwordState,
                imeAction = ImeAction.Done,
                errorText = "please enter a valid password"
            )
            ButtonItem(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp)
                    .align(CenterHorizontally),
                enableState = viewModel.loginButtonState,
                onClick = {
                    onLoginClick(viewModel, coroutineScope, navController, context)
                },
                text = "Login"
            )
            TextButton(
                onClick = {
                    viewModel.openDialog.value = true

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Forget Password?")
            }
            Text(
                text = "OR", modifier = Modifier
                    .align(CenterHorizontally),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedButtonItem(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .align(CenterHorizontally),
                enableState = viewModel.loginButtonState,
                text = "Create Account",
                onClick = {
                    navController.navigate(Screens.Register.route)
                }
            )
            if (viewModel.openDialog.value) {
                ForgetDialog(viewModel = viewModel, coroutineScope, context)

            }
        }
    }

}

@Composable
fun ForgetDialog(
    viewModel: LoginScreenViewModel, coroutineScope: CoroutineScope,
    context: Context
) {

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            viewModel.openDialog.value = false
        },
        title = {
            Text(text = "Reset Password")
        },
        dismissButton = {

            TextFieldUnit(
                hint = "Email",
                onImeAction = {
                    viewModel.dialogEmailErrorState.value =
                        viewModel.dialogEmailState.value.text.isEmpty()
                },
                modifier = Modifier,
                imeAction = ImeAction.Done,
                errorState = viewModel.dialogEmailErrorState,
                textState = viewModel.dialogEmailState,
                errorText = "please enter a valid email"
            )
        },
        confirmButton = {

            OutlinedButtonItem(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                enableState = viewModel.loginButtonState,
                text = "Reset Password",
                onClick = {
                    onResetClick(viewModel = viewModel, coroutineScope, context)
                    viewModel.openDialog.value = false
                }
            )
        },
    )
}

@Provides
fun onResetClick(

    viewModel: LoginScreenViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    viewModel.openDialog.value = false
    if (viewModel.dialogEmailState.value.text.isNotEmpty()) {
        coroutineScope.launch(Dispatchers.IO, CoroutineStart.UNDISPATCHED) {
            viewModel.resetPasswordResults().addOnCompleteListener { task ->
                task.addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Reset Mail Sent Please Check Your Email",
                        Toast.LENGTH_LONG
                    ).show()
                }
                task.addOnFailureListener { e ->
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }

        }


    } else {
        viewModel.loginButtonState.value = true

        Toast.makeText(
            context,
            "Please Enter a Valid Email and Password",
            Toast.LENGTH_LONG
        ).show()
    }


}

@Provides
fun onLoginClick(

    viewModel: LoginScreenViewModel,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    context: Context
) {
    viewModel.loginButtonState.value = false
    if (viewModel.emailState.value.text.isNotEmpty() && viewModel.passwordState.value.text.isNotEmpty()) {
        coroutineScope.launch(Dispatchers.IO, CoroutineStart.UNDISPATCHED) {
            viewModel.loginResults().addOnCompleteListener { task ->
                task.addOnSuccessListener {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.Login.route) {
                            inclusive = true

                        }
                    }
                    Toast.makeText(context, "Successful login", Toast.LENGTH_LONG)
                        .show()
                    viewModel.loginButtonState.value = true
                }
                task.addOnFailureListener { e ->
                    viewModel.loginButtonState.value = true
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }

        }


    } else {
        viewModel.loginButtonState.value = true

        Toast.makeText(
            context,
            "Please Enter a Valid Email and Password",
            Toast.LENGTH_LONG
        )
            .show()
    }


}

@Preview(showBackground = true)
@Composable
fun LoginScreenView() {
    LoginScreen(navController = rememberNavController())
}