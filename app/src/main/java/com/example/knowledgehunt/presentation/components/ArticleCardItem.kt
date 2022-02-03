package com.example.knowledgehunt.presentation.components

import android.graphics.Color.rgb
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import com.example.knowledgehunt.domain.models.ArticleItemData
import com.example.knowledgehunt.domain.models.Screens
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette
import java.text.SimpleDateFormat


@Composable
fun ArticleCardItem(
    articleItemData: ArticleItemData,
    author: String,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var palette by remember { mutableStateOf<Palette?>(null) }

    val typography = MaterialTheme.typography
    val df = SimpleDateFormat("dd MMM yyyy")
    Card(

        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .clickable {
                navController.navigate(Screens.ArticleDetails.route)
            }

            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),

        elevation = 8.dp
    ) {
        Column(
//            Modifier.background(color = Color(palette?.lightMutedSwatch?.rgb ?: rgb(255, 255, 255)))

        ) {

            GlideImage(
                imageModel = articleItemData.imageUrl,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .clip(RoundedCornerShape(16.dp))
                    .heightIn(max = 230.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                circularReveal = CircularReveal(800),
                bitmapPalette = BitmapPalette {
                    palette = it
                },
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = Color(
                        palette?.mutedSwatch?.rgb ?: MaterialTheme.colors.secondary.toArgb()
                    ),
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
            )

            Spacer(Modifier.height(4.dp))

            Text(
                color = Color(palette?.mutedSwatch?.rgb ?: rgb(0, 0, 0)),
                text = articleItemData.title!!,
                style = typography.h6,
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 8.dp, start = 8.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                color = Color(palette?.mutedSwatch?.rgb ?: rgb(0, 0, 0)),
                text = articleItemData.description!!,
                style = typography.subtitle1,
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 8.dp, start = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .padding(bottom = 4.dp, start = 8.dp),
            ) {
                Text(
                    text = author,
                    style = TextStyle(Color(palette?.mutedSwatch?.rgb ?: rgb(0, 0, 0))),
                )
                Text(
                    text = " ・ ",
                    style = TextStyle(Color(palette?.mutedSwatch?.rgb ?: rgb(0, 0, 0))),
                )
                Text(
                    text = "${articleItemData.reactions?.sum().toString()} Reactions",
                    style = TextStyle(Color(palette?.mutedSwatch?.rgb ?: rgb(0, 0, 0))),
                )
                Text(
                    text = " ・ ",
                    style = TextStyle(Color(palette?.mutedSwatch?.rgb ?: rgb(0, 0, 0))),
                )
                Text(
                    text = df.format(articleItemData.date?.toDate()?.time).toString(),
                    style = TextStyle(Color(palette?.mutedSwatch?.rgb ?: rgb(0, 0, 0))),
                )

            }
        }
    }
}



