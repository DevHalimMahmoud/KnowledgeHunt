package com.example.knowledgehunt.presentation.screens.about

import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import com.example.knowledgehunt.domain.models.Screens

@Composable
fun About(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                modifier = Modifier,
                factory = { context -> TextView(context) },
                update = {
                    it.text = HtmlCompat.fromHtml("Test", HtmlCompat.FROM_HTML_MODE_COMPACT)
                }
            )
            Button(onClick = { navController.navigate(Screens.Help.route) }) {

                Text(text = "click")
            }
        }
    }
}