package com.example.knowledgehunt.ui.components

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldUnit(
    textState: MutableState<TextFieldValue>,
    imeAction: ImeAction,
    onImeAction: () -> Unit = {},
    hint: String,
    errorText: String,
    errorState: MutableState<Boolean>,
    modifier: Modifier,
    KeyboardType: KeyboardType,
) {
    Box(modifier = modifier) {

        Column() {
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
                            text = hint,
                            style = MaterialTheme.typography.body1
                        )
                    }
                },
                isError = errorState.value,

                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(12.dp),
                textStyle = MaterialTheme.typography.body1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType,
                    imeAction = imeAction
                ),

                keyboardActions = KeyboardActions(
                    onAny = {
                        onImeAction()
                    }

                )
            )
            if (errorState.value) {
                Text(
                    text = errorText,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp),
                )
            }
        }
    }
}


@Composable
fun PasswordFiledUnit(
    passwordState: MutableState<TextFieldValue>,
    imeAction: ImeAction,
    onImeAction: () -> Unit = {},
    hint: String,
    errorText: String,
    errorState: MutableState<Boolean>,
    modifier: Modifier,
    keyboardType: KeyboardType
) {
    val showPassword = remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        Column() {
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Gray,

                    ),
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(12.dp),
                textStyle = MaterialTheme.typography.body1,
                label = {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = hint,
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
                isError = errorState.value,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        onImeAction()
                    }
                )

            )
            if (errorState.value) {
                Text(
                    text = errorText,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp),
                )
            }
        }
    }
}