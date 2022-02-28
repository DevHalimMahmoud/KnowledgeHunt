package com.example.knowledgehunt.presentation.screens.questions.addQuestion

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.presentation.components.BackTopBar
import com.example.knowledgehunt.presentation.components.TextFieldUnit
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddQuestionScreen(
    navController: NavHostController,
) {
    val viewModel: AddQuestionViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    if (viewModel.publishStates.value) {
        LaunchedEffect(
            Dispatchers.Main,
            CoroutineStart.DEFAULT
        ) {

            Toast
                .makeText(
                    context,
                    viewModel.publishError.value,
                    Toast.LENGTH_LONG
                )
                .show()
            if (viewModel.publishError.value == "Question Published Successfully!") {
                navController.popBackStack()
            }
            viewModel.publishStates.value = false
        }
    }

    Scaffold(
        topBar = {
            BackTopBar(
                title = "Ask a public question",
                buttonIcon = TablerIcons.ArrowBack,
                modifier = Modifier,
                onClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {

            if (viewModel.publishArticleProgressIndicator.value) {
                if (viewModel.notEmpty()) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                            .clickable {
                                viewModel.publishArticleProgressIndicator.value = false
                                coroutineScope
                                    .launch {
                                        viewModel.publishQuestion()
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
                style = typography.subtitle2,
                modifier = Modifier
                    .padding(end = 12.dp, start = 12.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            TextFieldUnit(
                hint = "e.g. How to handle concurrency in Kotlin?",
                onImeAction = {
                    viewModel.titleErrorState.value =
                        viewModel.titleState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.titleErrorState,
                textState = viewModel.titleState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text
            )
            Text(
                text = "Body",
                style = typography.h6,
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 12.dp, start = 12.dp, top = 12.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Include all the information someone would need to answer your question",
                style = typography.subtitle2,
                modifier = Modifier
                    .padding(end = 12.dp, start = 12.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            TextFieldUnit(
                hint = "Question Details",
                onImeAction = {
                    viewModel.contentErrorState.value =
                        viewModel.contentState.value.text.isEmpty()
                },
                modifier = Modifier
                    .height(200.dp)
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.contentErrorState,
                textState = viewModel.contentState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text,
            )

            Text(
                text = "Tags",
                style = typography.h6,
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 12.dp, start = 12.dp, top = 12.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Adding programming language and framework helps others find and answer your question faster",
                style = typography.subtitle2,
                modifier = Modifier
                    .padding(end = 12.dp, start = 12.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(modifier = Modifier) {
                TextFieldUnit(
                    hint = "Language",
                    onImeAction = {
                        viewModel.languageErrorState.value =
                            viewModel.languageState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.35f),
                    imeAction = ImeAction.Next,
                    errorState = viewModel.languageErrorState,
                    textState = viewModel.languageState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Text
                )
                TextFieldUnit(
                    hint = "Framework or Tool",
                    onImeAction = {
                        viewModel.toolErrorState.value =
                            viewModel.toolState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.65f),
                    imeAction = ImeAction.Next,
                    errorState = viewModel.toolErrorState,
                    textState = viewModel.toolState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Text
                )
            }
        }
    }
}

