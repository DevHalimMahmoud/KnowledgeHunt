package com.example.knowledgehunt.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.Screens

@Composable
fun Articles(openDrawer: () -> Unit, navController: NavHostController) {
    Scaffold(
        floatingActionButton = {

            Icon(
                imageVector = Icons.Default.PostAdd,
                tint = MaterialTheme.colors.primary,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate(Screens.AddArticle.route)
                    },
            )
        },
    ) {

    }

}