package com.abdelHalimMahmoud.knowledgehunt.presentation.screens.article.myArticleDetails

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.BackTopBar
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.OutlinedButtonItem
import com.abdelHalimMahmoud.knowledgehunt.presentation.components.TextFieldUnit
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyArticleDetailsScreen(
    navController: NavHostController,
) {
    val screenViewModel: MyArticleDetailsScreenViewModel = hiltViewModel()
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
            if (screenViewModel.publishError.value == "Article Updated Successfully!") {
                navController.popBackStack()
            }
            screenViewModel.publishStates.value = false
        }
    }
    Scaffold(
        topBar = {
            BackTopBar(
                title = "Edit Article",
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
        }) {
        if (screenViewModel.deleteDocumentState.value && screenViewModel.deleteImageState.value) {
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
            TextFieldUnit(
                hint = "Title",
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
            TextFieldUnit(
                hint = "Description",
                onImeAction = {
                    screenViewModel.descriptionErrorState.value =
                        screenViewModel.descriptionState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = screenViewModel.descriptionErrorState,
                textState = screenViewModel.descriptionState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text
            )
            TextFieldUnit(
                hint = "Article Content",
                onImeAction = {
                    screenViewModel.contentErrorState.value =
                        screenViewModel.contentState.value.text.isEmpty()
                },
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = screenViewModel.contentErrorState,
                textState = screenViewModel.contentState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text,
            )
        }
    }
}
