package com.example.knowledgehunt.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.knowledgehunt.domain.models.ArticleItemData
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ArticleCardItem(articleItemData: ArticleItemData, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        GlideImage( // CoilImage, FrescoImage
            imageModel = articleItemData.imageUrl,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .heightIn(min = 180.dp)
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium),

            // shows a shimmering effect when loading an image.
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = MaterialTheme.colors.secondary,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
            ),
            // shows an error text message when request failed.
            failure = {

            })

        Spacer(Modifier.height(16.dp))

        Text(
            text = articleItemData.title,
            style = typography.h5,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = articleItemData.subtitle,
            style = typography.subtitle1,
            modifier = Modifier.padding(bottom = 4.dp)
        )


        Row(modifier = Modifier) {
            Text(
                text = "Author",
                style = TextStyle(Color.Gray),

                )
            Text(
                text = " - ",
                style = TextStyle(Color.Gray),
            )
            Text(
                text = "Date",
                style = TextStyle(Color.Gray),
            )
        }


    }
}



