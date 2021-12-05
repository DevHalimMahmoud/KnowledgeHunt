package com.example.knowledgehunt.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonItem(
    modifier: Modifier,
    onClick: () -> Unit = {},
    enableState: MutableState<Boolean>,
    text: String
) {

    Button(
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = Color.LightGray
        ),
        onClick = {onClick()},
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),

        enabled = enableState.value
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun OutlinedButtonItem(
    modifier: Modifier,
    onClick: () -> Unit = {},
    enableState: MutableState<Boolean>,
    text: String
) {

    OutlinedButton(

        onClick = {onClick()},
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),

        enabled = enableState.value
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp)
        )
    }
}