package com.example.knowledgehunt.ui.Screens

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.example.knowledgehunt.models.NavigationItem
import com.example.knowledgehunt.ui.components.BottomNavigationBar
import com.example.knowledgehunt.ui.components.TopBar

val HomeBottomNavigationItems = listOf(
    NavigationItem("home", Icons.Filled.Home, "Home"),
    NavigationItem("music", Icons.Filled.Menu, "Music"),
    NavigationItem("movies", Icons.Filled.ArrowBack, "Movies"),
    NavigationItem("books", Icons.Filled.Home, "Books"),
    NavigationItem("profile", Icons.Filled.Home, "Profile"),
)

@Composable
fun Home(openDrawer: () -> Unit) {

    Scaffold(
        topBar = {
            TopBar(
                title = "Home",
                buttonIcon = Icons.Filled.Menu,
                onButtonClicked = { openDrawer() }
            )
        },
        bottomBar = { BottomNavigationBar(HomeBottomNavigationItems) }
    ) {
        /* Add code later */
    }

}