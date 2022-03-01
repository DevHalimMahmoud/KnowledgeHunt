package com.example.knowledgehunt.presentation.screens.questions.viewQuestions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.domain.models.Screens
import com.example.knowledgehunt.presentation.components.NoDataDesign
import com.example.knowledgehunt.presentation.components.QuestionCardItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.firebase.firestore.DocumentSnapshot
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Code

@Composable
fun ViewQuestionsScreen(navController: NavController) {
    val viewModel: ViewQuestionsViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Icon(
                imageVector = FontAwesomeIcons.Solid.Code,
                tint = MaterialTheme.colors.primary,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate(Screens.AddQuestion.route)
                    },
            )
        },
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                viewModel.refresh()
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (viewModel.questionState.value.isEmpty()) {
                NoDataDesign(
                    title = "No questions available at the moment check your internet connection",
                    image = painterResource(R.drawable.ic_empty),
                )
            } else {

                LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {

                    items(viewModel.questionState.value) { question ->

                        val authorData: MutableState<DocumentSnapshot?> = remember {
                            mutableStateOf(null)
                        }

                        if (authorData.value == null) {
                            LaunchedEffect(key1 = true) {
                                viewModel.getAuthorName(question, authorData)
                            }
                        }
                        QuestionCardItem(
                            authorData,
                            question,
                            click = {
//                            navController.navigate(Screens.ArticleDetails.route)
                            },
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}