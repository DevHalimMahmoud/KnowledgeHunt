package com.example.knowledgehunt.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knowledgehunt.presentation.screens.mcq.takeMCQ.MCQTestScreenViewModel

@Composable
fun MCQQuestionItem(
    index: Int,
    viewModel: MCQTestScreenViewModel
) {
    val rightAnswer = viewModel.questions?.get(index)?.get("right_ans_key").toString()

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp,
    ) {
        Column() {
            Text(
                text = viewModel.questions?.get(index)?.get("question").toString(),
                modifier = Modifier.padding(8.dp)
            )

            Row() {
                OutlinedButton(
                    onClick = {

                        if (viewModel.questions?.get(index)?.get(rightAnswer)
                                .toString() == viewModel.questions?.get(index)?.get("ans1")
                                .toString()
                        ) {
                            viewModel.score.value += 10
                        }
                        viewModel.answeredQuestions.value += 1
                        viewModel.questions?.removeAt(index)
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = viewModel.questions?.get(index)?.get("ans1").toString(),
                        fontSize = 12.sp
                    )
                }
                OutlinedButton(
                    onClick = {

                        if (viewModel.questions?.get(index)?.get(rightAnswer)
                                .toString() == viewModel.questions?.get(index)?.get("ans2")
                                .toString()
                        ) {
                            viewModel.score.value += 10
                        }
                        viewModel.answeredQuestions.value += 1
                        viewModel.questions?.removeAt(index)
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = viewModel.questions?.get(index)?.get("ans2").toString(),
                        fontSize = 12.sp
                    )
                }
            }
            Row() {
                OutlinedButton(
                    onClick = {

                        if (viewModel.questions?.get(index)?.get(rightAnswer)
                                .toString() == viewModel.questions?.get(index)?.get("ans3")
                                .toString()
                        ) {
                            viewModel.score.value += 10
                        }
                        viewModel.answeredQuestions.value += 1
                        viewModel.questions?.removeAt(index)
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = viewModel.questions?.get(index)?.get("ans3").toString(),
                        fontSize = 12.sp
                    )
                }
                OutlinedButton(
                    onClick = {
                        if (viewModel.questions?.get(index)?.get(rightAnswer)
                                .toString() == viewModel.questions?.get(index)?.get("ans4")
                                .toString()
                        ) {
                            viewModel.score.value += 10
                        }
                        viewModel.answeredQuestions.value += 1
                        viewModel.questions?.removeAt(index)
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = viewModel.questions?.get(index)?.get("ans4").toString(),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}