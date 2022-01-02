package com.example.knowledgehunt.ui.screens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.models.Screens
import com.example.knowledgehunt.ui.components.BackTopBar
import com.example.knowledgehunt.ui.components.OutlinedButtonItem
import com.example.knowledgehunt.ui.components.TextFieldUnit
import com.example.knowledgehunt.viewModels.AddArticleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddArticleScreen(
    navController: NavHostController,
) {
    val viewModel: AddArticleViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    if (viewModel.PublishStates.value) {
        LaunchedEffect(
            Dispatchers.Main,
            CoroutineStart.DEFAULT
        ) {

            Toast
                .makeText(
                    context,
                    viewModel.PublishError.value,
                    Toast.LENGTH_LONG
                )
                .show()
            if (viewModel.PublishError.value == "Article Published Successfully!") {
                navController.popBackStack()
            }
            viewModel.PublishStates.value = false
        }

    }

    Scaffold(

        topBar = {
            BackTopBar(
                title = Screens.AddArticle.title,
                buttonIcon = Icons.Default.ArrowBack,
                modifier = Modifier,
                onClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {

            if (viewModel.PublishArticleProgressIndicator.value) {
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
                                viewModel.PublishArticleProgressIndicator.value = false
                                coroutineScope
                                    .launch {
                                        viewModel.publishArticle()
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

            ArticleImage(viewModel, coroutineScope, context)

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
                    .height(300.dp)

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

@Composable
fun ArticleImage(
    viewModel: AddArticleViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {

    val launcher: ManagedActivityResultLauncher<String, Uri?> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            viewModel.imageUri?.value = uri

            if (viewModel.imageUri?.value != null) {
                viewModel.ImageCompressionProgressIndicator.value = true
                Toast.makeText(
                    context,
                    "Compressing Image...",
                    Toast.LENGTH_SHORT
                ).show()
                handelArticleImage(viewModel = viewModel, coroutineScope, context)
            }

        }

    Column(Modifier.fillMaxWidth()) {
        if (viewModel.bitmap.value == null) {
            Image(
                Icons.Default.AddPhotoAlternate,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(250.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
            )
        } else {
            Image(
                bitmap = viewModel.bitmap.value!!.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }
        if (viewModel.ImageCompressionProgressIndicator.value) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator()
            }

        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedButtonItem(
        onClick = {

            launcher.launch("image/*")
        },
        text = "Add Article Image",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp),
        enableState = remember { mutableStateOf(true) },
    )
}

fun handelArticleImage(
    viewModel: AddArticleViewModel,
    coroutineScope: CoroutineScope,
    context: Context,
) {
    coroutineScope.launch(Dispatchers.Default, CoroutineStart.DEFAULT) {
        viewModel.compressProfileImage(context = context)

    }.invokeOnCompletion {
        viewModel.ImageCompressionProgressIndicator.value = false
    }
}