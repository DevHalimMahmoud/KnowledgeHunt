package com.example.knowledgehunt.presentation.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
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
    profileImageUrl: Uri,
    ShowDialog: () -> Unit,
    Search: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,

        title = {
            Text(
                text = title, color = MaterialTheme.colors.onSurface
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

            IconButton(onClick = { Search() }) {
                Icon(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape),
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                )
            }

            GlideImage(
                // CoilImage, FrescoImage
                imageModel = profileImageUrl,
                modifier = modifier
                    .size(40.dp)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .clickable { ShowDialog() },
                // shows a shimmering effect when loading an image.
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = MaterialTheme.colors.secondary,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                circularReveal = CircularReveal(800),
            )

            IconButton(onClick = { Logout() }) {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Default.Logout,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
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
            Text(text = title, color = MaterialTheme.colors.onSurface)
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
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

