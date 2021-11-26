package com.example.knowledgehunt.ui.components

import android.R
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.knowledgehunt.models.NavigationItem


val items = listOf(
    NavigationItem("home", Icons.Filled.Home, "Home"),
    NavigationItem("music", Icons.Filled.Menu, "Music"),
    NavigationItem("movies", Icons.Filled.ArrowBack, "Movies"),
    NavigationItem("books", Icons.Filled.Home, "Books"),
    NavigationItem("profile", Icons.Filled.Home, "Profile"),
)

@Composable
fun BottomNavigationBar(items: List<NavigationItem>) {

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.holo_purple),
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(items)
}