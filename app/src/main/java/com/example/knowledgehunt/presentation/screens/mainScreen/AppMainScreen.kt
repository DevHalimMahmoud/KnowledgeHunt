package com.example.knowledgehunt.presentation.screens.mainScreen

import SplashScreen
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.domain.models.Screens
import com.example.knowledgehunt.presentation.components.AppDrawer
import com.example.knowledgehunt.presentation.components.ProfileDialog
import com.example.knowledgehunt.presentation.components.TopBar
import com.example.knowledgehunt.presentation.screens.about.About
import com.example.knowledgehunt.presentation.screens.article.addArticle.AddArticleScreen
import com.example.knowledgehunt.presentation.screens.article.articleDetailsScreen.ArticleDetailsScreen
import com.example.knowledgehunt.presentation.screens.article.myArticleDetails.MyArticleDetailsScreen
import com.example.knowledgehunt.presentation.screens.article.myArticles.MyArticles
import com.example.knowledgehunt.presentation.screens.article.viewArticle.Articles
import com.example.knowledgehunt.presentation.screens.help.Screen
import com.example.knowledgehunt.presentation.screens.home.HomeScreen
import com.example.knowledgehunt.presentation.screens.login.LoginScreen
import com.example.knowledgehunt.presentation.screens.profile.EditProfileScreen
import com.example.knowledgehunt.presentation.screens.registration.RegisterScreen
import com.example.knowledgehunt.presentation.screens.search.SearchScreen
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers

@Composable
fun AppMainScreen() {
    val viewModel: AppMainScreenViewModel = hiltViewModel()
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
            if (currentRoute == Screens.Articles.route || currentRoute == Screens.Home.route || currentRoute == Screens.About.route) {
                LaunchedEffect(Dispatchers.Default, CoroutineStart.DEFAULT) {
                    if (viewModel.loggedIn()) {
                        viewModel.getTopBarProfileImage()
                        viewModel.getUserName()
                        viewModel.getUserEmail()
                    }
                }
                TopBar(

                    title = currentRoute.toString().uppercase(),
                    scope = scope,
                    scaffoldState = scaffoldState,
                    buttonIcon = painterResource(id = R.drawable.logo_no_text),
                    modifier = Modifier
                        .border(1.dp, color = MaterialTheme.colors.onError, CircleShape)
                        .clip(CircleShape),
                    Logout = {

                        viewModel.logoutResults()
                        navController.navigate(Screens.Login.route) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    },
                    profileImageUrl = viewModel.profileImageUrl.value,
                    ShowDialog = { viewModel.showProfileDialog.value = true },
                    Search = { navController.navigate(Screens.Search.route) }
                )
                drawerGesturesEnabled.value = true
            }
        },
        drawerBackgroundColor = MaterialTheme.colors.onPrimary,
//        drawerScrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute.orEmpty(),
                navController = navController,
                scope = scope,
                scaffoldState = scaffoldState,
                userData = viewModel.userData,
                profileImageUrl = viewModel.profileImageUrl,
                email = viewModel.email
            )
        },
    ) {
        ProfileDialog(
            viewModel.showProfileDialog,
            viewModel.profileImageUrl,
            viewModel.username,
            viewModel.email,
            navController
        )
        Navigation(navController = navController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController, startDestination = Screens.Splash.route) {
        composable(Screens.Home.route) {
            HomeScreen(
                navController
            )
        }
        composable(Screens.Articles.route) {
            Articles(
                navController
            )
        }
        composable(Screens.About.route) {
            About(
                navController
            )
        }
        composable(Screens.Splash.route) {
            SplashScreen(
                navController = navController
            )
        }
        composable(Screens.Help.route) {
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
        composable(Screens.AddArticle.route) {
            AddArticleScreen(
                navController = navController
            )
        }
        composable(Screens.ArticleDetails.route)
        {
            ArticleDetailsScreen(
                navController
            )
        }
        composable(Screens.MyArticles.route)
        {
            MyArticles(
                navController
            )
        }
        composable(Screens.MyArticlesDetails.route)
        {
            MyArticleDetailsScreen(
                navController
            )
        }
        composable(Screens.EditProfile.route)
        {
            EditProfileScreen(
                navController
            )
        }
        composable(Screens.Search.route)
        {
            SearchScreen(
                navController
            )
        }
    }
}


@Preview
@Composable
fun DefaultPreview() {

    AppMainScreen()

}