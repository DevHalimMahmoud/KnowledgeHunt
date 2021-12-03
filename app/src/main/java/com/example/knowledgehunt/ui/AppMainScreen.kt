package com.example.knowledgehunt.ui

import SplashScreen
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.knowledgehunt.ui.components.AppDrawer
import com.example.knowledgehunt.ui.components.Screens
import kotlinx.coroutines.launch

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
                            popUpTo(navController.graph.startDestinationId) {

                                inclusive = true

                            }
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
                composable(Screens.Login.route) {
                    LoginScreen(
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