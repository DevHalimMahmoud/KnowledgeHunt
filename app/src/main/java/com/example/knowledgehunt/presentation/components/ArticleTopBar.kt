package com.example.knowledgehunt.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack

@Composable
fun ArticleTopBar(
    title: String?,
    back: () -> Unit,
    modifier: Modifier,
    profileImageUrl: Uri,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Row(
                horizontalArrangement = Start,
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.Start)
            ) {

                GlideImage(
                    // CoilImage, FrescoImage
                    imageModel = profileImageUrl,
                    modifier = modifier
                        .size(40.dp)

                        .clip(CircleShape),
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
                Text(
                    text = "Published By: \n$title" ?: "Publisher Name",
                    style = MaterialTheme.typography.subtitle2,
                    color = LocalContentColor.current,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1.5f)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                back()
            }) {
                Icon(
                    TablerIcons.ArrowBack, "", tint = MaterialTheme.colors.primary
                )
            }
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onPrimary,
    )
}
