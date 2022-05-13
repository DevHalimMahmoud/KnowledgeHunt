package com.abdelHalimMahmoud.knowledgehunt.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abdelHalimMahmoud.knowledgehunt.domain.models.QuestionItemData
import com.abdelHalimMahmoud.knowledgehunt.presentation.theme.blue
import com.abdelHalimMahmoud.knowledgehunt.presentation.utils.calculate0LessOrMore
import com.abdelHalimMahmoud.knowledgehunt.presentation.utils.calculateIncrement0orMore
import com.abdelHalimMahmoud.knowledgehunt.presentation.utils.calculateIncrement50
import com.google.firebase.firestore.DocumentSnapshot
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import compose.icons.FontAwesomeIcons
import compose.icons.TablerIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Wrench
import compose.icons.tablericons.Code
import java.text.SimpleDateFormat

@Composable
fun QuestionCardItem(
    authorData: MutableState<DocumentSnapshot?>,
    question: QuestionItemData,
    click: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val df = SimpleDateFormat("dd MMM yyyy")
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .clickable {
                click()
            }
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Column {
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .border(1.dp, Color(calculate0LessOrMore((question.question_downvotes?.let {
                            question.question_upvotes?.minus(
                                it
                            )
                        })?.toLong())), RoundedCornerShape(4.dp))
                        .align(End)
                ) {
                    Text(
                        text = (question.question_downvotes?.let {
                            question.question_upvotes?.minus(
                                it
                            )
                        }).toString() + " votes",
                        color = Color(calculate0LessOrMore((question.question_downvotes?.let {
                            question.question_upvotes?.minus(
                                it
                            )
                        })?.toLong())),
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .border(
                            1.dp,
                            Color(calculateIncrement0orMore(question.answers?.size?.toLong())),
                            RoundedCornerShape(4.dp)
                        )
                        .align(End)
                ) {
                    Text(
                        text = question.answers?.size.toString() + " answers",
                        color = Color(calculateIncrement0orMore(question.answers?.size?.toLong())),
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .border(
                            1.dp,
                            Color(calculateIncrement50(question.views?.toLong())),
                            RoundedCornerShape(4.dp)
                        )
                        .align(End)
                ) {
                    Text(
                        text = question.views.toString() + " views",
                        color = Color(calculateIncrement50(question.views?.toLong())),
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = question.title.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        letterSpacing = 0.4.sp
                    ),
                    modifier = Modifier,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    color = blue
                )
                Row {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .background(blue.copy(0.25f), RoundedCornerShape(4.dp))
                            .weight(1f)
                            .wrapContentWidth()

                    ) {
                        Row(
                            Modifier
                                .padding(2.dp)
                                .align(CenterStart)
                                .wrapContentWidth()
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Wrench,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(CenterVertically),
                                tint = blue
                            )
                            Text(
                                text = question.field.toString(),
                                color = blue,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp, vertical = 4.dp)
                                    .weight(1f),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .background(blue.copy(0.25f), RoundedCornerShape(4.dp))
                            .weight(1f)
                            .wrapContentWidth()

                    ) {
                        Row(
                            Modifier
                                .padding(2.dp)
                                .align(CenterStart)
                                .wrapContentWidth()
                        ) {
                            Icon(
                                imageVector = TablerIcons.Code,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(CenterVertically),
                                tint = blue
                            )
                            Text(
                                text = question.prog_language.toString(),
                                color = blue,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp, vertical = 4.dp)
                                    .weight(1f)
                                    .align(CenterVertically),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(2.dp)

                    ) {
                        Column {
                            Text(
                                text = authorData.value?.get("f_name").toString(),
                                style = TextStyle(Color.Gray, 12.sp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )

                            Text(
                                text = df.format(question.date?.toDate()?.time).toString(),
                                style = TextStyle(Color.Gray, 12.sp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1

                            )
                        }
                        GlideImage(
                            // CoilImage, FrescoImage
                            imageModel = "https://firebasestorage.googleapis.com/v0/b/knowledge-hunt-4c809.appspot.com/o/user%2F${authorData.value?.id}.JPEG?alt=media&token=69e2b92c-2e9b-460e-b79c-d7e300439234",
                            modifier = Modifier
                                .size(35.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(4.dp)),
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
                    }
                }
            }
        }
    }
}

