package com.example.knowledgehunt.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.domain.models.Screens
import com.example.knowledgehunt.domain.utils.ArticleArguments
import com.example.knowledgehunt.domain.utils.MCQTestArguments
import com.example.knowledgehunt.domain.utils.QuestionArguments
import com.example.knowledgehunt.presentation.components.*
import com.google.firebase.firestore.DocumentSnapshot

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeScreenViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            color = Color.DarkGray,
            text = "Recent Articles",
            style = TextStyle(
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .padding(12.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (viewModel.articleState.value.isEmpty()) {
            Box(modifier = Modifier.size((LocalConfiguration.current.screenWidthDp - 30).dp)) {
                NoDataDesign(
                    title = "No articles available at the moment check your internet connection",
                    image = painterResource(R.drawable.ic_empty),
                )
            }
        } else {


            LazyRow(state = rememberLazyListState(), modifier = Modifier.wrapContentSize()) {

                items(
                    viewModel.articleState.value
                ) { article ->
                    val authorData: MutableState<DocumentSnapshot?> = remember {
                        mutableStateOf(null)
                    }

                    LaunchedEffect(key1 = true) {
                        viewModel.getAuthorName(article.user_id.toString(), authorData)
                    }


                    ArticleCardItem(
                        article,
                        authorData.value?.get("f_name").toString(),
                        navController = navController,
                        click = {
                            ArticleArguments.instance?.author =
                                authorData.value?.get("f_name").toString()
                            ArticleArguments.instance?.articleItemData = article
                            navController.navigate(Screens.ArticleDetails.route)
                        },
                        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp - 30).dp),
                    )
                }
            }
        }

        Text(
            color = Color.DarkGray,
            text = "Latest Questions",
            style = TextStyle(
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .padding(12.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (viewModel.questionState.value.isEmpty()) {
            Box(modifier = Modifier.size((LocalConfiguration.current.screenWidthDp - 30).dp)) {
                NoDataDesign(
                    title = "No questions available at the moment check your internet connection",
                    image = painterResource(R.drawable.ic_empty),
                )
            }
        } else {

            LazyRow(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {

                items(viewModel.questionState.value) { question ->

                    val authorData: MutableState<DocumentSnapshot?> = remember {
                        mutableStateOf(null)
                    }

                    if (authorData.value == null) {
                        LaunchedEffect(key1 = true) {
                            viewModel.getAuthorName(question.user_id.toString(), authorData)
                        }
                    }
                    QuestionCardItem(
                        authorData,
                        question,
                        click = {
                            QuestionArguments.instance?.questionItemData = question
                            QuestionArguments.instance?.authorData = authorData
                            navController.navigate(Screens.QuestionDetails.route)
                        },
                        navController = navController,
                        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp - 30).dp)
                    )
                }
            }
        }
        Text(
            color = Color.DarkGray,
            text = "Available Tests",
            style = TextStyle(
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .padding(12.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        SimpleAlertDialog(viewModel.showDialog, "Are you sure you want to start the test?") {
            viewModel.showDialog.value = false
            navController.navigate(
                Screens.TakeMCQ.route
            )
        }
        if (viewModel.MCQState.value.isEmpty()) {
            Box(modifier = Modifier.size((LocalConfiguration.current.screenWidthDp - 30).dp)) {
                NoDataDesign(
                    title = "No MCQ tests available at the moment check your internet connection",
                    image = painterResource(R.drawable.ic_empty),
                )
            }
        } else {
            LazyRow(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {

                items(
                    viewModel.MCQState.value
                ) { MCQItem ->
                    MCQCardItem(
                        MCQItem,
                        navController = navController,
                        click = {
                            viewModel.showDialog.value = true

                            MCQTestArguments.instance?.itemData = MCQItem

                        },
                        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp - 30).dp)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding()
                    .size(12.dp)
            )
        }

    }
}