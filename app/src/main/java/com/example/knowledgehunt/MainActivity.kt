package com.example.knowledgehunt

import SplashScreen
import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.ActionBar
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable

import kotlinx.coroutines.launch
import androidx.navigation.compose.rememberNavController
import com.example.knowledgehunt.ui.components.Screens
import com.example.knowledgehunt.ui.theme.KnowledgeHuntTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.knowledgehunt.ui.Articles
import com.example.knowledgehunt.ui.About
import com.example.knowledgehunt.ui.HomeScreen
import com.example.knowledgehunt.ui.Screen
import com.example.knowledgehunt.ui.components.AppDrawer


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            KnowledgeHuntTheme {
                actionBar?.show()
                AppMainScreen()
            }

        }

    }
}

@Composable
fun AppMainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }
        ModalDrawer(

            drawerState = drawerState,
            gesturesEnabled = drawerState.isClosed,
            drawerContent = {

                    AppDrawer(
                        currentRoute = currentRoute.orEmpty(),
                        onDestinationClicked = { route ->
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )

            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.Splash.route,


                ) {
                composable(Screens.Home.route) {
                    HomeScreen(
                        openDrawer = {
                            openDrawer()
                        }, navController
                    )
                }
                composable(Screens.Articles.route) {
                    Articles(
                        openDrawer = {
                            openDrawer()
                        }, navController
                    )
                }
                composable(Screens.Help.route) {
                    About(
                        openDrawer = {
                            openDrawer()
                        }, navController
                    )
                }
                composable(Screens.Splash.route) {

                    SplashScreen(
                        navController = navController
                    )
                }
                composable(Screens.Test.route) {
                    Screen(
                        navController = navController
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {

    AppMainScreen()

}