package kangparks.android.vostom.screens.timer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kangparks.android.vostom.navigations.Content
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CountDownScreen(navController : NavHostController){
    val countValue = remember{ mutableIntStateOf(4) }

    LaunchedEffect(null){
        while(countValue.value > 0){
            countValue.value -= 1
            delay(1000)
        }
        navController.navigate(Content.LearningSecondSing.route)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        AnimatedVisibility(
            visible = countValue.value ==3,
            enter = fadeIn()+ scaleIn(),
            exit = fadeOut()+ scaleOut(),
            ) {
            Text(
                text = "3",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )
        }

        AnimatedVisibility(
            visible = countValue.value ==2,
            enter = fadeIn()+ scaleIn(),
            exit = fadeOut()+ scaleOut(),
        ) {
            Text(
                text = "2",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )
        }

        AnimatedVisibility(
            visible = countValue.value ==1,
            enter = fadeIn()+ scaleIn(),
            exit = fadeOut()+ scaleOut(),
        ) {
            Text(
                text = "1",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }


}