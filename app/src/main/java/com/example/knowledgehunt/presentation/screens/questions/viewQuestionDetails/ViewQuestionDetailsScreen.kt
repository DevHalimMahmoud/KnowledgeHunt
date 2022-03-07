package com.example.knowledgehunt.presentation.screens.questions.viewQuestionDetails

import android.net.Uri
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.example.knowledgehunt.domain.utils.QuestionArguments
import com.example.knowledgehunt.presentation.components.ArticleTopBar
import com.example.knowledgehunt.presentation.components.TextFieldUnit
import com.example.knowledgehunt.presentation.theme.blue
import com.example.knowledgehunt.presentation.theme.green
import com.example.knowledgehunt.presentation.theme.lvl9
import com.google.firebase.auth.FirebaseAuth
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import compose.icons.FontAwesomeIcons
import compose.icons.TablerIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Eye
import compose.icons.fontawesomeicons.solid.Wrench
import compose.icons.tablericons.ChevronsDown
import compose.icons.tablericons.ChevronsUp
import compose.icons.tablericons.Code
import compose.icons.tablericons.Message
import java.text.SimpleDateFormat

@Composable
fun ViewQuestionDetailsScreen(navController: NavController) {
    val df = SimpleDateFormat("dd MMM yyyy")
    Scaffold(bottomBar = {

        Column(modifier = Modifier.background(MaterialTheme.colors.onPrimary)) {
            Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 6.dp)
            ) {

                GlideImage(
                    // CoilImage, FrescoImage
                    imageModel = Uri.parse("https://firebasestorage.googleapis.com/v0/b/knowledge-hunt-4c809.appspot.com/o/user%2F${FirebaseAuth.getInstance().currentUser?.uid}.JPEG?alt=media&token=69e2b92c-2e9b-460e-b79c-d7e300439234"),
                    modifier = Modifier
                        .size(40.dp)
                        .align(CenterVertically)
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

                TextFieldUnit(
                    hint = "Add an answer",
                    onImeAction = {
                        //                        viewModel.emailErrorState.value = viewModel.emailState.value.text.isEmpty()
                        //                        viewModel.focusRequester.requestFocus()
                    },
                    modifier = Modifier
                        .height(73.dp)
                        .padding(vertical = 8.dp, horizontal = 4.dp)
                        .align(CenterVertically)
                        .weight(1f),
                    imeAction = ImeAction.Next,
                    errorState = remember {
                        mutableStateOf(false)
                    },
                    textState = remember {
                        mutableStateOf(TextFieldValue(""))
                    },
                    errorText = "",
                    KeyboardType = KeyboardType.Email
                )
                IconButton(onClick = { }, Modifier.align(CenterVertically)) {
                    Icon(
                        modifier = Modifier.align(CenterVertically),
                        imageVector = Icons.Filled.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                    )
                }

            }
        }
    },
        topBar = {
            ArticleTopBar(

                title = "Asked By: \n${
                    QuestionArguments.instance?.authorData?.value?.get("f_name")
                        .toString() + " " + QuestionArguments.instance?.authorData?.value?.get("l_name")
                        .toString()
                }",
                profileImageUrl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/knowledge-hunt-4c809.appspot.com/o/user%2F${QuestionArguments.instance?.questionItemData?.user_id}.JPEG?alt=media&token=69e2b92c-2e9b-460e-b79c-d7e300439234"),
                modifier = Modifier
                    .border(1.dp, color = MaterialTheme.colors.onError, CircleShape)
                    .clip(CircleShape),
                back = ({ navController.popBackStack() }),
            )
        }) {

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {


            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Column(modifier = Modifier.align(CenterVertically)) {
                    Text(
                        text = QuestionArguments.instance?.questionItemData?.title
                            .toString(),
                        style = TextStyle(MaterialTheme.colors.onBackground, 20.sp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 10,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = df.format(QuestionArguments.instance?.questionItemData?.date?.toDate())
                            .toString(),
                        style = TextStyle(Color.Gray, 16.sp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .align(Start)
                    ) {
                        Row(
                            Modifier
                                .padding(8.dp)

                                .wrapContentWidth()
                                .background(blue.copy(0.25f), RoundedCornerShape(4.dp)),
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.Wrench,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(25.dp)
                                    .align(CenterVertically)
                                    .padding(4.dp),
                                tint = blue
                            )
                            Text(
                                text = QuestionArguments.instance?.questionItemData?.field.toString(),
                                color = blue,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp, vertical = 4.dp)
                                    .align(CenterVertically),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(blue.copy(0.25f), RoundedCornerShape(4.dp))
                                .wrapContentWidth()
                        ) {
                            Icon(
                                imageVector = TablerIcons.Code,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(25.dp)
                                    .align(CenterVertically)
                                    .padding(2.dp),
                                tint = blue
                            )
                            Text(
                                text = QuestionArguments.instance?.questionItemData?.prog_language.toString(),
                                color = blue,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp, vertical = 4.dp)
                                    .align(CenterVertically),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    AndroidView(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 8.dp),
                        factory = { context -> TextView(context) },
                        update = {

                            it.text = HtmlCompat.fromHtml(
                                QuestionArguments.instance?.questionItemData?.content.toString(),
                                HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS
                            )
                        }
                    )
                    Row(modifier = Modifier) {
                        Row(Modifier.weight(1f)) {
                            IconButton(modifier = Modifier, onClick = {}) {
                                Icon(
                                    imageVector = TablerIcons.ChevronsUp,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .align(CenterVertically),
                                    tint = green
                                )
                            }
                            Text(
                                text = (QuestionArguments.instance?.questionItemData?.question_downvotes?.let {
                                    QuestionArguments.instance?.questionItemData?.question_upvotes?.minus(
                                        it
                                    )
                                }).toString(),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .align(CenterVertically),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                            IconButton(modifier = Modifier, onClick = {}) {
                                Icon(
                                    imageVector = TablerIcons.ChevronsDown,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .align(CenterVertically),
                                    tint = lvl9
                                )
                            }
                        }
                        Row(Modifier.weight(0.75f)) {
                            IconButton(modifier = Modifier, onClick = {}) {
                                Icon(
                                    imageVector = TablerIcons.Message,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .align(CenterVertically),
                                    tint = Color.Gray
                                )
                            }
                            Text(
                                text = QuestionArguments.instance?.questionItemData?.answers?.size.toString(),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .padding(start = 4.dp),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                        Row(Modifier.weight(0.75f)) {
                            IconButton(modifier = Modifier, onClick = {}) {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.Eye,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(45.dp)
                                        .align(CenterVertically),
                                    tint = Color.Gray
                                )
                            }
                            Text(
                                text = QuestionArguments.instance?.questionItemData?.views.toString(),
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .padding(start = 4.dp),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }
            }
            Divider(
                color = MaterialTheme.colors.onSurface.copy(alpha = .08f),
                modifier = Modifier
                    .height(35.dp)
                    .padding(vertical = 4.dp)
            )




            Divider(
                color = Color.Transparent,
                modifier = Modifier
                    .height(100.dp)
                    .padding(vertical = 4.dp)
            )

        }
    }

}
