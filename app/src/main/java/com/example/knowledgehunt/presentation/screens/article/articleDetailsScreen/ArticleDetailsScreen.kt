package com.example.knowledgehunt.presentation.screens.article.articleDetailsScreen

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.palette.graphics.Palette
import com.amrdeveloper.reactbutton.ReactButton
import com.example.knowledgehunt.domain.utils.ArticleArguments
import com.example.knowledgehunt.domain.utils.FbReactions
import com.example.knowledgehunt.presentation.components.ArticleTopBar
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette

@Composable
fun ArticleDetailsScreen(
    navController: NavHostController,
) {
    var i = 0
    val viewModel: ArticleDetailsScreenViewModel = hiltViewModel()
    var palette by remember { mutableStateOf<Palette?>(null) }

    Scaffold(
        topBar = {
            ArticleTopBar(
                title = ArticleArguments.instance?.author,
                profileImageUrl = viewModel.authorImageUrl.value,
                modifier = Modifier
                    .border(1.dp, color = MaterialTheme.colors.onError, CircleShape)
                    .clip(CircleShape),
                back = ({ navController.popBackStack() }),
            )
        },
        floatingActionButton = {

            AndroidView(
                modifier = Modifier
                    .wrapContentSize()
                    .wrapContentSize(),
                factory = { context -> ReactButton(context) },
            ) {

                it.setDimAmount(0.5f)
                it.setBackgroundColor(Color.Transparent.toArgb())

                it.setReactions(*FbReactions.reactions)
                it.defaultReaction = FbReactions.defaultReact
                it.setEnableReactionTooltip(true)
                it.setOnReactionChangeListener { reaction ->

                    viewModel.updateReactionsList()

                }
            }

        },
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {

            GlideImage(
                imageModel = ArticleArguments.instance?.articleItemData?.imageUrl,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .heightIn(max = 230.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(
                        Color(
                            palette?.mutedSwatch?.rgb ?: MaterialTheme.colors.onSecondary.toArgb()
                        )
                    ),

                circularReveal = CircularReveal(800),
                bitmapPalette = BitmapPalette {
                    palette = it
                },
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = Color(
                        palette?.mutedSwatch?.rgb ?: MaterialTheme.colors.onSecondary.toArgb()
                    ),
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
            )

            Spacer(Modifier.height(4.dp))

            AndroidView(
                modifier = Modifier.wrapContentSize(),
                factory = { context -> TextView(context) },
                update = {
                    it.text = HtmlCompat.fromHtml(
                        ArticleArguments.instance?.articleItemData?.content!!,
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                }
            )

        }
    }

}
