package com.example.knowledgehunt.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.palette.graphics.Palette
import com.example.knowledgehunt.domain.models.MCQItemData
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette

@Composable
fun MCQCardItem(item: MCQItemData, navController: NavHostController, click: () -> Unit) {
    var palette by remember { mutableStateOf<Palette?>(null) }

    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
        elevation = 0.dp
    ) {
        Column(modifier = Modifier
            .clickable {
                click()
            }) {
            GlideImage(
                imageModel = item.image_url,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
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
                        palette?.darkVibrantSwatch?.rgb ?: MaterialTheme.colors.secondary.toArgb()
                    ),
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
            )

            Spacer(Modifier.height(8.dp))

            Text(
                color = Color(
                    palette?.darkVibrantSwatch?.rgb ?: android.graphics.Color.rgb(
                        0,
                        0,
                        0
                    )
                ),
                text = item.title.toString(),
                style = TextStyle(fontFamily = FontFamily.Default, fontSize = 25.sp),
                modifier = Modifier
                    .padding(bottom = 4.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                color = Color(
                    palette?.darkVibrantSwatch?.rgb ?: android.graphics.Color.rgb(
                        0,
                        0,
                        0
                    )
                ),
                text = item.language.toString(),
                style = TextStyle(fontFamily = FontFamily.Default, fontSize = 15.sp),
                modifier = Modifier
                    .padding(bottom = 4.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier,
            ) {
                Text(
                    text = "${item.questions?.size?.toString()} Total Questions",
                    style = TextStyle(
                        Color(
                            palette?.darkVibrantSwatch?.rgb
                                ?: MaterialTheme.colors.onBackground.toArgb()
                        ), fontSize = 12.sp
                    ),
                )
                Text(
                    text = " ・ ",
                    style = TextStyle(
                        Color(
                            palette?.darkVibrantSwatch?.rgb
                                ?: MaterialTheme.colors.onBackground.toArgb()
                        ), fontSize = 12.sp
                    ),
                )
                Text(
                    text = "${item.try_num?.toString()} Took This Test",
                    style = TextStyle(
                        Color(
                            palette?.darkVibrantSwatch?.rgb
                                ?: MaterialTheme.colors.onBackground.toArgb()
                        ), fontSize = 12.sp
                    ),
                )
            }
        }
    }
    Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f), modifier = Modifier.padding(bottom = 8.dp))
}
