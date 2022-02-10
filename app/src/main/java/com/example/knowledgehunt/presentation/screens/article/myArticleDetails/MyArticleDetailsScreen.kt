package com.example.knowledgehunt.presentation.screens.article.myArticleDetails

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.models.Screens
import com.example.knowledgehunt.presentation.components.BackTopBar
import com.example.knowledgehunt.presentation.components.TextFieldUnit
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MyArticleDetailsScreen(
    navController: NavHostController,
) {
    val viewModel: MyArticleDetailsViewModel = hiltViewModel()
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
            if (viewModel.publishError.value == "Article Updated Successfully!") {
                navController.popBackStack()
            }
            viewModel.publishStates.value = false
        }
    }

    Scaffold(

        topBar = {
            BackTopBar(
                title = Screens.MyArticlesDetails.title,
                buttonIcon = TablerIcons.ArrowBack,
                modifier = Modifier,
                onClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {

            if (viewModel.publishArticleProgressIndicator.value) {
                if (viewModel.notEmpty()) {
                    Image(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                            .clickable {
                                viewModel.publishArticleProgressIndicator.value = false
                                coroutineScope
                                    .launch {
                                        viewModel.updateArticle()
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

//            ArticleImage(viewModel, coroutineScope, context)

            TextFieldUnit(
                hint = "Title",
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
            TextFieldUnit(
                hint = "Description",
                onImeAction = {
                    viewModel.descriptionErrorState.value =
                        viewModel.descriptionState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.descriptionErrorState,
                textState = viewModel.descriptionState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text
            )
            TextFieldUnit(
                hint = "Article Content",
                onImeAction = {
                    viewModel.contentErrorState.value =
                        viewModel.contentState.value.text.isEmpty()
                },
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.contentErrorState,
                textState = viewModel.contentState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text,
            )
        }
    }
}
