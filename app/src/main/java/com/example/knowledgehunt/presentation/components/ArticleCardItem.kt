package com.example.knowledgehunt.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.knowledgehunt.domain.models.ArticleItemData
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import java.text.SimpleDateFormat


@Composable
fun ArticleCardItem(articleItemData: ArticleItemData, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    val df = SimpleDateFormat("dd MMM yyyy")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)

    ) {

        GlideImage(
            imageModel = "https://tech-echo.com/wp-content/uploads/2020/09/android-11-update-features.jpg",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .wrapContentHeight()
                .fillMaxWidth(),

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

        Spacer(Modifier.height(12.dp))

        Text(
            text = articleItemData.title!!,
            style = typography.h5,
            modifier = Modifier
                .padding(bottom = 4.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = articleItemData.description!!,
            style = typography.subtitle1,
            modifier = Modifier
                .padding(bottom = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(modifier = Modifier) {
            Text(
                text = "Author",
                style = TextStyle(Color.Gray),
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )
            Text(
                text = " - ",
                style = TextStyle(Color.Gray),
            )
            Text(
                text = df.format(articleItemData.date?.toDate()?.time).toString(),
                style = TextStyle(Color.Gray),
            )
        }
        
        Box(
            modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .heightIn(1.dp)
                .background(color = Color.LightGray)
        )
    }
}



