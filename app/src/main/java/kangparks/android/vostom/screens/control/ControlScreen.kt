package kangparks.android.vostom.screens.control

 import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.R
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.navigations.Nav
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.splash.RequestState
import kangparks.android.vostom.viewModel.splash.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun ControlScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel
){
    val context = LocalContext.current

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    val requestState = splashViewModel.isReceivedRequestLearningState.observeAsState(RequestState.BeforeRequest)
    val currentLearningState = splashViewModel.currentLearningState.observeAsState(LearningState.BeforeLearning)

    LaunchedEffect(key1 = null){
        delay(1000)
        val token = getAccessToken(context)

        if(token == null){
            navController.navigate(Nav.AUTH){
                navController.popBackStack()
            }
            return@LaunchedEffect
        }
        else{
            splashViewModel.getCurrentLearningState(
                token = token,
                context = context
            )
        }
    }

    LaunchedEffect(key1 = requestState.value){
        if(requestState.value == RequestState.AfterRequest){
            if(currentLearningState.value == LearningState.AfterLearning){
                navController.navigate(HomeContent.Home.route){
                    navController.popBackStack()
                }
            }
            else if(currentLearningState.value == LearningState.BeforeLearning){
                navController.navigate(LearningContent.Guide.route){
                    navController.popBackStack()
                }
            }
            else{
                navController.navigate(LearningContent.Loading.route){
                    navController.popBackStack()
                }
            }
        }
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(if(isDarkTheme)Color(0xFF161517) else Color(0xFFBEAFE2)),
        color = MaterialTheme.colorScheme.background
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if(isDarkTheme)Color(0xFF161517) else Color(0xFFEBE7F4)),
            contentAlignment = Alignment.Center,
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "vostom_logo",
                modifier = Modifier.size(280.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.screen_subtitle),
                contentDescription = "vostom_subtitle",
                modifier = Modifier
                    .size(190.dp)
                    .padding(top = 55.dp)
            )
        }
        Box(
            modifier = Modifier.padding(bottom = 100.dp),
            contentAlignment = Alignment.BottomCenter,
            ){
            CircularProgressIndicator(
                modifier = Modifier.width(48.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}