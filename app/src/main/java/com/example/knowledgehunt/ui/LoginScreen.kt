package com.example.knowledgehunt.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.ui.components.ButtonItem
import com.example.knowledgehunt.ui.components.OutlinedButtonItem
import com.example.knowledgehunt.ui.components.Password
import com.example.knowledgehunt.ui.components.TextFieldUnit

@Composable
fun LoginScreen(navController: NavHostController) {
    val focusRequester = remember { FocusRequester() }
    val emailState: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) }
    val emailErrorState: MutableState<Boolean> = remember { mutableStateOf(false) }
    val passwordState: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) }
    val passwordErrorState: MutableState<Boolean> = remember { mutableStateOf(false) }
    val loginButtonState: MutableState<Boolean> = remember { mutableStateOf(true) }
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
                text = "Email",
                onImeAction = { focusRequester.requestFocus() },
                modifier = Modifier,
                imeAction = ImeAction.Next,
                errorState = emailErrorState,
                textState = emailState,
            )
            Password(
                text = "password",
                onImeAction = { focusRequester.freeFocus() },
                modifier = Modifier.focusRequester(focusRequester),
                errorState = passwordErrorState,
                passwordState = passwordState,
                imeAction = ImeAction.Done
            )
            ButtonItem(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp)
                    .align(CenterHorizontally),
                enableState = loginButtonState,
                onClick = { loginButtonState.value = false },
                text = "Login"
            )
            TextButton(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Forget Password?")
            }
            Text(text = "Or", modifier = Modifier
                
                .align(CenterHorizontally),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedButtonItem(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .align(CenterHorizontally), enableState = loginButtonState, text = "SignUp"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenView() {
    LoginScreen(navController = rememberNavController())
}