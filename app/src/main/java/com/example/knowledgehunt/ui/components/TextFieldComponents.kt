package com.example.knowledgehunt.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldUnit(
    textState: MutableState<TextFieldValue>,
    imeAction: ImeAction,
    onImeAction: () -> Unit = {},
    text: String,
    errorState: MutableState<Boolean>,
    modifier: Modifier
) {


    Column(Modifier.padding(16.dp)) {


        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Gray,
            ),
            value = textState.value,
            onValueChange = {
                textState.value = it

            },
            label = {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            textStyle = MaterialTheme.typography.body1,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
            keyboardActions = KeyboardActions(
                onAny = {
                    errorState.value = true
                    onImeAction()
                }

            )

        )
        if (errorState.value) {
            Text(
                text = "Error message",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}


@Composable
fun Password(
    passwordState: MutableState<TextFieldValue>,
    imeAction: ImeAction,
    onImeAction: () -> Unit = {},
    text: String,
    errorState: MutableState<Boolean>,
    modifier: Modifier
) {
    val showPassword = remember { mutableStateOf(false) }
    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Gray,

                ),
            value = passwordState.value,
            onValueChange = {
                passwordState.value = it
            },
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            textStyle = MaterialTheme.typography.body1,
            label = {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1
                    )
                }
            },
            trailingIcon = {
                if (showPassword.value) {
                    IconButton(onClick = { showPassword.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },

            keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
            keyboardActions = KeyboardActions(
                onAny = {
                    errorState.value = true

                    onImeAction()
                }
            )
        )
        if (errorState.value) {
            Text(
                text = "Error message",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}