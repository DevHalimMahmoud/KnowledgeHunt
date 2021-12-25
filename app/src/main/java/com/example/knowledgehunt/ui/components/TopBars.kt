package com.example.knowledgehunt.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    title: String = "",
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    buttonIcon: Painter,
    modifier: Modifier,
    Logout: () -> Unit,
    profileImage: Bitmap
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title, color = Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(buttonIcon, "", tint = MaterialTheme.colors.primary)
            }
        },
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = { /* TODO: Open search */ },
                    Modifier.size(40.dp)
                ) {
                    Icon(

                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,

                        )
                }
                IconButton(
                    onClick = { /* TODO: Open account? */ },
                    Modifier
                        .size(45.dp)
                        .padding(2.dp)
                ) {
                    Image(
                        bitmap = profileImage.asImageBitmap(),
                        contentDescription = null,
                        Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
                    )
                }
                IconButton(
                    onClick = { Logout() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}

@Composable
fun BackTopBar(
    title: String,
    buttonIcon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title, color = Color.Black)
        },
        navigationIcon = {
            IconButton(onClick = {
                onClick()
            }) {
                Icon(
                    buttonIcon, "", tint = MaterialTheme.colors.primary
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}

