package com.example.knowledgehunt.presentation.screens.mcq.takeMCQ

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.utils.MCQTestArguments
import com.example.knowledgehunt.presentation.components.BackTopBar
import com.example.knowledgehunt.presentation.components.MCQQuestionItem
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MCQTestScreen(navController: NavHostController) {
    val viewModel: MCQTestScreenViewModel = hiltViewModel()
    val context = LocalContext.current

    remember {
        object : CountDownTimer(100000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                viewModel.timeLeft.value = (100000 - millisUntilFinished) / 100000.toFloat()
            }

            override fun onFinish() {
                if (viewModel.answeredQuestions.value != 10) {
                    navController.popBackStack()
                }
            }

        }.start()

    }

    if (viewModel.answeredQuestions.value == 10) {
        Toast.makeText(
            context,
            viewModel.score.value.toString() + " " + viewModel.questions?.size.toString(),
            Toast.LENGTH_LONG
        )
            .show()
        remember {

            navController.popBackStack()

        }

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
