package com.example.knowledgehunt.ui

import SplashScreen
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.ui.components.AppDrawer
import com.example.knowledgehunt.ui.components.Screens
import com.example.knowledgehunt.ui.components.TopBar
import com.example.knowledgehunt.viewModels.AppMainScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AppMainScreen() {
    val viewModel: AppMainScreenViewModel = viewModel()
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val drawerGesturesEnabled: MutableState<Boolean> = remember { mutableStateOf(false) }
    val currentRoute = navBackStackEntry?.destination?.route
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    // If you want the drawer from the right side, uncomment the following
    // CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
    Scaffold(
        drawerGesturesEnabled = drawerGesturesEnabled.value,

        scaffoldState = scaffoldState,
        topBar = {
            if (currentRoute == Screens.Articles.route || currentRoute == Screens.Home.route || currentRoute == Screens.Help.route) {
                TopBar(
                    title = currentRoute.toString().uppercase(),
                    scope = scope,
                    scaffoldState = scaffoldState,
                    buttonIcon = painterResource(id = R.drawable.logo_no_text),
                    modifier = Modifier,
                    Logout = {
                        scope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
                            viewModel.LogoutResults()
                        }
                        navController.navigate(Screens.Login.route) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    },

                    viewModel.profileImage.value
                )
                drawerGesturesEnabled.value = true
            }
        },
//        drawerScrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
        drawerContent = {
            AppDrawer(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController,
                currentRoute = currentRoute.orEmpty()
            )
        },
    ) {
        Navigation(navController = navController, viewModel, scope)
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: AppMainScreenViewModel,
    scope: CoroutineScope
) {
    NavHost(navController, startDestination = Screens.Splash.route) {
        composable(Screens.Home.route) {
            scope.launch(Dispatchers.Default, CoroutineStart.DEFAULT) {
                if (viewModel.loggedIn()) {
                    viewModel.getTopBarProfileImage()
                }
            }

            HomeScreen(
                openDrawer = {

                }, navController
            )
        }
        composable(Screens.Articles.route) {
            Articles(
                openDrawer = {
                }, navController
            )
        }
        composable(Screens.Help.route) {
            About(
                openDrawer = {
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
        composable(Screens.Register.route) {
            RegisterScreen(
                navController = navController
            )
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {

    AppMainScreen()

}