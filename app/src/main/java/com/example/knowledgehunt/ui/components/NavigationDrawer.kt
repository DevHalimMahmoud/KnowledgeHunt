package com.example.knowledgehunt.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.knowledgehunt.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class Screens(val title: String, val route: String) {
    object Home : Screens("Home", "home")
    object Articles : Screens("Articles", "articles")
    object Help : Screens("Help", "help")
    object Splash : Screens("Splash", "splash")
    object Login : Screens("Login", "login")
    object Register : Screens("Create Account", "register")
    object Test : Screens("Test", "test")

}

private val screens = listOf(
    Screens.Home,
    Screens.Articles,
    Screens.Help
)
private val icon = listOf(
    Icons.Filled.Home,
    Icons.Filled.Star,
    Icons.Filled.AccountBox
)

@Composable
fun AppDrawer(
    currentRoute: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(ScrollState(0))) {
        HuntLogo(Modifier.padding(16.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        Spacer(Modifier.height(24.dp))
        screens.forEachIndexed { index, screen ->

            DrawerButton(
                icon = icon[index],
                label = screen.title,
                isSelected = currentRoute == screen.route,
                action = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                    // Close drawer
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
        }
    }
}


@Composable
private fun HuntLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        HuntIcon()
        Spacer(Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_with_title),
            contentDescription = null, // decorative element
        )
    }
}

@Composable
fun HuntIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_with_title),
        contentDescription = null, // decorative element
    )
}

@Composable
private fun DrawerButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationIcon(
                    icon = icon,
                    isSelected = isSelected,
                    contentDescription = null, // decorative
                    tintColor = textIconColor
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )
            }
        }
    }
}

@Composable
fun NavigationIcon(
    icon: ImageVector,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tintColor: Color? = null,
) {
    val imageAlpha = if (isSelected) {
        1f
    } else {
        0.6f
    }

    val iconTintColor = tintColor ?: if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }

    Image(
        modifier = modifier,
        imageVector = icon,
        contentDescription = contentDescription,
        contentScale = ContentScale.Inside,
        colorFilter = ColorFilter.tint(iconTintColor),
        alpha = imageAlpha
    )
}

//@Preview("Drawer contents")
//@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun PreviewAppDrawer() {
//    KnowledgeHuntTheme {
//        Surface {
//            AppDrawer(
//                currentRoute = screens[0].route,
//                modifier = Modifier,
//                navController =,
//                CoroutineScope()
//
//            )
//        }
//    }
//}