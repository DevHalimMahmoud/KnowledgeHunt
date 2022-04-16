package com.example.knowledgehunt.presentation.screens.questions.viewQuestionDetails

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.domain.utils.QuestionArguments
import com.example.knowledgehunt.presentation.components.ArticleTopBar
import com.example.knowledgehunt.presentation.components.NoDataDesign
import com.example.knowledgehunt.presentation.components.TextFieldUnit
import com.example.knowledgehunt.presentation.theme.blue
import com.example.knowledgehunt.presentation.theme.green
import com.example.knowledgehunt.presentation.theme.lvl9
import com.example.knowledgehunt.presentation.utils.calculateEmptyText
import com.example.knowledgehunt.presentation.utils.calculateIncrement5
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@Composable
fun ViewQuestionDetailsScreen(navController: NavController) {
    val df = SimpleDateFormat("dd MMM yyyy")
    val viewModel: ViewQuestionDetailsScreenViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

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
                        viewModel.addAnswer()
                        keyboardController?.hide()
                    },
                    modifier = Modifier
                        .height(73.dp)
                        .padding(vertical = 8.dp, horizontal = 4.dp)
                        .align(CenterVertically)
                        .weight(1f),
                    imeAction = ImeAction.Send,
                    errorState = remember {
                        mutableStateOf(false)
                    },
                    textState = viewModel.answerState,
                    errorText = "",
                    KeyboardType = KeyboardType.Text
                )
                IconButton(
                    onClick = {
                        viewModel.addAnswer()
                        keyboardController?.hide()
                    },
                    Modifier.align(CenterVertically),
                    enabled = viewModel.answerState.value.text.isNotBlank()
                ) {
                    Icon(
                        modifier = Modifier.align(CenterVertically),
                        imageVector = Icons.Filled.Send,
                        contentDescription = null,
                        tint = Color(
                            calculateEmptyText(
                                viewModel.answerState.value.text
                            )
                        ),
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

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                viewModel.refresh()
            },
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {
                item {
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
                                    IconButton(
                                        modifier = Modifier,
                                        onClick = {
                                            QuestionArguments.instance?.questionItemData?.question_upvotes =
                                                QuestionArguments.instance?.questionItemData?.question_upvotes as Long + 1
                                            viewModel.vote()
                                            viewModel.updateVoteCount(
                                                QuestionArguments.instance?.questionItemData?.user_id.toString(),
                                                "num_upvote"
                                            )
                                        }) {
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
                                    IconButton(
                                        modifier = Modifier,
                                        onClick = {
                                            QuestionArguments.instance?.questionItemData?.question_downvotes =
                                                QuestionArguments.instance?.questionItemData?.question_downvotes as Long + 1
                                            viewModel.vote()
                                            viewModel.updateVoteCount(
                                                QuestionArguments.instance?.questionItemData?.user_id.toString(),
                                                "num_downvote"
                                            )
                                        }) {
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
                                                .size(40.dp)
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
                }
                item {
                    Divider(
                        color = MaterialTheme.colors.onSurface.copy(alpha = .08f),
                        modifier = Modifier
                            .height(20.dp)
                            .padding(top = 4.dp)
                    )
                }
                if (QuestionArguments.instance?.questionItemData?.answers?.isEmpty() == true) {
                    item {
                        NoDataDesign(
                            title = "No Answers Yet",
                            image = painterResource(R.drawable.ic_search),
                        )
                    }
                } else {

                    items(QuestionArguments.instance?.questionItemData?.answers!!.reversed()) { question ->

                        val userData: MutableState<DocumentSnapshot?> = remember {
                            mutableStateOf(null)
                        }

                        if (userData.value == null) {
                            LaunchedEffect(key1 = true) {
                                viewModel.getUserData(question, userData)
                            }
                        }

                        Divider(
                            color = MaterialTheme.colors.onSurface.copy(alpha = .08f),
                            modifier = Modifier
                                .height(15.dp)
                        )
                        Column(Modifier.padding(12.dp)) {
                            Row() {
                                GlideImage(
                                    // CoilImage, FrescoImage
                                    imageModel = Uri.parse(
                                        "https://firebasestorage.googleapis.com/v0/b/knowledge-hunt-4c809.appspot.com/o/user%2F${
                                            question?.get(
                                                "user_id"
                                            ).toString()
                                        }.JPEG?alt=media&token=69e2b92c-2e9b-460e-b79c-d7e300439234"
                                    ),
                                    modifier = Modifier
                                        .size(45.dp)
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .align(CenterVertically),
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
                                    text = userData.value?.get("f_name")
                                        .toString() + " " + userData.value?.get("l_name")
                                        .toString(),
                                    style = TextStyle(Color.DarkGray, 16.sp),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 10,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .align(CenterVertically),
                                    fontWeight = FontWeight.Bold
                                )
                                Column(
                                    modifier = Modifier
                                        .align(CenterVertically)
                                        .fillMaxWidth()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 4.dp, vertical = 4.dp)
                                            .align(End)
                                            .background(
                                                Color(calculateIncrement5(userData.value?.get("score") as Long?)).copy(
                                                    alpha = 0.35f
                                                ),
                                                RoundedCornerShape(4.dp)
                                            )
                                            .border(
                                                0.5.dp,
                                                Color.LightGray,
                                                RoundedCornerShape(4.dp)
                                            )
                                    ) {
                                        Text(
                                            text = userData.value?.get("score")
                                                .toString() + "\nScore",
                                            fontFamily = FontFamily.SansSerif,
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colors.onSurface,
                                            modifier = Modifier.padding(
                                                horizontal = 4.dp,
                                                vertical = 4.dp
                                            )

                                        )
                                    }
                                }
                            }
                            Text(
                                text = question?.get("answer").toString(),
                                style = TextStyle(Color.DarkGray, 16.sp),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 20,
                                modifier = Modifier
                                    .padding(vertical = 12.dp, horizontal = 4.dp)
                                    .align(Start),
                            )
                            Row(Modifier.align(Start)) {
                                IconButton(
                                    modifier = Modifier,
                                    onClick = {
                                        question!!["upvotes"] = (question["upvotes"] as Long) + 1
                                        viewModel.vote()
                                        viewModel.updateVoteCount(
                                            question["user_id"].toString(),
                                            "num_upvote"
                                        )
                                    }) {
                                    Icon(
                                        imageVector = TablerIcons.ChevronsUp,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(35.dp)
                                            .align(CenterVertically),
                                        tint = green
                                    )
                                }
                                Text(
                                    text = ((question?.get("downvotes") as Long).let {
                                        (question["upvotes"] as Long).minus(it)
                                    }).toString(),
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .align(CenterVertically),
                                    maxLines = 1,
                                    textAlign = TextAlign.Center
                                )
                                IconButton(modifier = Modifier, onClick = {
                                    question["downvotes"] = (question["downvotes"] as Long) + 1
                                    viewModel.vote()
                                    viewModel.updateVoteCount(
                                        question["user_id"].toString(),
                                        "num_downvote"
                                    )
                                }) {
                                    Icon(
                                        imageVector = TablerIcons.ChevronsDown,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(35.dp)
                                            .align(CenterVertically),
                                        tint = lvl9
                                    )
                                }
                            }
                        }
                    }

                }

                item {
                    Divider(
                        color = MaterialTheme.colors.onSurface.copy(alpha = .08f),
                        modifier = Modifier
                            .height(15.dp)
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
    }
}
