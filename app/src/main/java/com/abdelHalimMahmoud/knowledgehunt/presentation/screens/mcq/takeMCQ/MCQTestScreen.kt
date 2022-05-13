package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.mcq.takeMCQ

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abdelHalimMahmoud.knowledgehunt.domain.utils.MCQTestArguments
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.BackTopBar
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.HuntIcon
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.MCQQuestionItem
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.OutlinedButtonItem
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MCQTestScreen(navController: NavHostController) {
    val viewModel: MCQTestScreenViewModel = hiltViewModel()
    val context = LocalContext.current

    if (viewModel.answeredQuestions.value == 10) {
        viewModel.timer.cancel()
        TestResults(navController = navController, viewModel = viewModel)

    } else if (viewModel.timeLeft.value == 0f) {

        TestResults(navController = navController, viewModel = viewModel)

    }

    Scaffold(topBar = {
        BackTopBar(
            title = "${MCQTestArguments.instance?.itemData?.title.toString()}\n ${(100 - (viewModel.timeLeft.value * 100).toInt()).toString()} second left",
            buttonIcon = TablerIcons.ArrowBack,
            modifier = Modifier
        ) {
            navController.popBackStack()
        }
    },
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {

                LinearProgressIndicator(
                    progress = viewModel.timeLeft.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )
            }

        }) {

        LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {

            items(
                viewModel.questions?.size!!
            ) { index ->
                MCQQuestionItem(index, viewModel)

            }
        }
    }
}


@Composable
fun TestResults(navController: NavHostController, viewModel: MCQTestScreenViewModel) {
    Dialog(onDismissRequest = { }) {
        Surface(
            modifier = Modifier,
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colors.onPrimary,
            contentColor = MaterialTheme.colors.onSurface
        ) {

            Column(modifier = Modifier) {
                HuntIcon(modifier = Modifier.fillMaxWidth())
                Text(
                    text = MCQTestArguments.instance?.itemData?.title.toString(),
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "You Scored ${viewModel.score.value}/100",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,

                    )
                OutlinedButtonItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    enableState = remember {
                        mutableStateOf(true)
                    },
                    text = "Finish", onClick = { navController.popBackStack() }
                )
            }

        }
    }
}
