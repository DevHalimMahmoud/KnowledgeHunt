package com.abdelHalimMahmoud.knowledgehunt.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
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
                    .fillMaxHeight(),
                shape = RoundedCornerShape(12.dp),
                textStyle = MaterialTheme.typography.body1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType,
                    imeAction = imeAction
                ),

                keyboardActions = KeyboardActions(
                    onSend = {
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