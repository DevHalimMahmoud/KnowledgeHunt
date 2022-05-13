package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.questions.myQuestionDetails

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abdelHalimMahmoud.knowledgehunt.domain.models.Screens
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.BackTopBar
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.OutlinedButtonItem
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.TextFieldUnit
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyQuestionsDetailsScreen(
    navController: NavHostController,
) {
    val screenViewModel: MyQuestionDetailsScreenViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    if (screenViewModel.publishStates.value) {
        LaunchedEffect(
            Dispatchers.Main,
            CoroutineStart.DEFAULT
        ) {

            Toast
                .makeText(
                    context,
                    screenViewModel.publishError.value,
                    Toast.LENGTH_LONG
                )
                .show()
            if (screenViewModel.publishError.value == "Question Updated Successfully!") {
                navController.popBackStack()
            }
            screenViewModel.publishStates.value = false
        }
    }

    Scaffold(
        topBar = {
            BackTopBar(
                title = Screens.MyQuestionsDetails.title,
                buttonIcon = TablerIcons.ArrowBack,
                modifier = Modifier,
                onClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {

            if (screenViewModel.publishArticleProgressIndicator.value) {
                if (screenViewModel.notEmpty()) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                            .clickable {
                                screenViewModel.publishArticleProgressIndicator.value = false
                                coroutineScope
                                    .launch {
                                        screenViewModel.updateArticle()
                                    }
                            },
                    )
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                )
            }
        },
    ) {
        if (screenViewModel.deleteDocumentState.value) {
            Dialog(onDismissRequest = { }) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            GlideImage(
                imageModel = "https://storage.googleapis.com/glaze-ecom.appspot.com/images/NbEGVQIpL/free/free.png",
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = MaterialTheme.colors.secondary,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                circularReveal = CircularReveal(500),
            )

            OutlinedButtonItem(
                onClick = {
                    screenViewModel.deleteArticle(navController)
                },
                text = "Delete Article",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 8.dp)
                    .border(1.dp, Color.Red, RoundedCornerShape(16.dp)),
                enableState = remember { mutableStateOf(true) },
            )

            Text(
                text = "Title",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 12.dp, start = 12.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Be specific and imagine you’re asking a question to another person",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(end = 12.dp, start = 12.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            TextFieldUnit(
                hint = "e.g. How to handle concurrency in Kotlin?",
                onImeAction = {
                    screenViewModel.titleErrorState.value =
                        screenViewModel.titleState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = screenViewModel.titleErrorState,
                textState = screenViewModel.titleState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text
            )
            Text(
                text = "Body",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 12.dp, start = 12.dp, top = 12.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Include all the information someone would need to answer your question",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(end = 12.dp, start = 12.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            TextFieldUnit(
                hint = "Question Details",
                onImeAction = {
                    screenViewModel.contentErrorState.value =
                        screenViewModel.contentState.value.text.isEmpty()
                },
                modifier = Modifier
                    .height(200.dp)
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = screenViewModel.contentErrorState,
                textState = screenViewModel.contentState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text,
            )

            Text(
                text = "Tags",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 12.dp, start = 12.dp, top = 12.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Adding programming language and framework helps others find and answer your question faster",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(end = 12.dp, start = 12.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(modifier = Modifier) {
                TextFieldUnit(
                    hint = "Language",
                    onImeAction = {
                        screenViewModel.languageErrorState.value =
                            screenViewModel.languageState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.35f),
                    imeAction = ImeAction.Next,
                    errorState = screenViewModel.languageErrorState,
                    textState = screenViewModel.languageState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Text
                )
                TextFieldUnit(
                    hint = "Framework or Tool",
                    onImeAction = {
                        screenViewModel.toolErrorState.value =
                            screenViewModel.toolState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.65f),
                    imeAction = ImeAction.Next,
                    errorState = screenViewModel.toolErrorState,
                    textState = screenViewModel.toolState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Text
                )
            }
        }
    }
}

