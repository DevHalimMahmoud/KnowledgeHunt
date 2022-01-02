import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.knowledgehunt.R
import com.example.knowledgehunt.models.Screens
import com.example.knowledgehunt.viewModels.SplashScreenViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = viewModel()

) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000L)
        if (viewModel.currentUser == null) {

            navController.navigate(Screens.Login.route) {
                popUpTo(Screens.Splash.route) {
                    inclusive = true

                }
            }
        } else {
            navController.navigate(Screens.Articles.route) {
                popUpTo(Screens.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
    Scaffold {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_no_text),
                contentDescription = null, // decorative element
                Modifier
                    .scale(scale = scale.value)
                    .align(Alignment.Center),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenView() {
    SplashScreen(navController = rememberNavController())
}