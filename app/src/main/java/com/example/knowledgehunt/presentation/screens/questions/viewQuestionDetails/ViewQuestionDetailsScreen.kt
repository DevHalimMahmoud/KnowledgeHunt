package com.example.knowledgehunt.presentation.screens.questions.viewQuestionDetails

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.knowledgehunt.domain.utils.QuestionArguments
import com.example.knowledgehunt.presentation.components.ArticleTopBar
import com.example.knowledgehunt.presentation.components.TextFieldUnit
import com.google.firebase.auth.FirebaseAuth
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import java.text.SimpleDateFormat

@Composable
fun ViewQuestionDetailsScreen(navController: NavController) {
    val df = SimpleDateFormat("dd MMM yyyy")
    Scaffold(bottomBar = {

        Column(modifier = Modifier) {
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
                    errorText = "please enter a valid email",
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
    }) {

        Column(modifier = Modifier) {
            ArticleTopBar(

                title = "Asked By: \n${
                    QuestionArguments.instance?.authorData?.value?.get("user_name").toString()
                }",
                profileImageUrl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/knowledge-hunt-4c809.appspot.com/o/user%2F${QuestionArguments.instance?.questionItemData?.user_id}.JPEG?alt=media&token=69e2b92c-2e9b-460e-b79c-d7e300439234"),
                modifier = Modifier
                    .border(1.dp, color = MaterialTheme.colors.onError, CircleShape)
                    .clip(CircleShape),
                back = ({ navController.popBackStack() }),
            )

            Row(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Column(modifier = Modifier.align(CenterVertically)) {
                    Text(
                        text = QuestionArguments.instance?.authorData?.value?.get("f_name")
                            .toString() + " " + QuestionArguments.instance?.authorData?.value?.get("l_name")
                            .toString(),
                        style = TextStyle(MaterialTheme.colors.onBackground, 16.sp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = df.format(QuestionArguments.instance?.questionItemData?.date?.toDate())
                            .toString(),
                        style = TextStyle(Color.Gray, 12.sp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

            }
        }

    }
}