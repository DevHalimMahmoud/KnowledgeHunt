package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.abdelHalimMahmoud.knowledgehunt.R
import com.abdelHalimMahmoud.knowledgehunt.domain.models.Screens
import com.abdelHalimMahmoud.knowledgehunt.domain.utils.ArticleArguments
import com.abdelHalimMahmoud.knowledgehunt.domain.utils.MCQTestArguments
import com.abdelHalimMahmoud.knowledgehunt.domain.utils.QuestionArguments
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.*
import com.google.firebase.firestore.DocumentSnapshot

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel: SearchScreenViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            SearchTopBar(
                query = viewModel.query.value,
                onQueryChanged = viewModel::onQueryChanged,
                onExecuteSearch = {

                },
                back = { navController.popBackStack() }
            )
        }
    ) {
        if (viewModel.articleState.value.isEmpty() && viewModel.questionState.value.isEmpty() && viewModel.MCQState.value.isEmpty()) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .padding(12.dp),
                )
            }
        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                if (viewModel.articleState.value.isNotEmpty()) {
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

                    LazyRow(
                        state = rememberLazyListState(),
                        modifier = Modifier.wrapContentSize()
                    ) {

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

                if (viewModel.questionState.value.isNotEmpty()) {
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

                if (viewModel.MCQState.value.isNotEmpty()) {
                    SimpleAlertDialog(
                        viewModel.showDialog,
                        "Are you sure you want to start the test?"
                    ) {
                        viewModel.showDialog.value = false
                        navController.navigate(
                            Screens.TakeMCQ.route
                        )
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
    }
}