package kangparks.android.vostom.screens.learning

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.item.CountDownItem
import kangparks.android.vostom.navigations.Content
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CountDownScreen(
    navController : NavHostController,
    destination : String
){
    val countValue = remember{ mutableIntStateOf(4) }

    BackHandler(enabled = true) { }

    LaunchedEffect(null){
        while(countValue.value > 0){
            countValue.value -= 1
            delay(1000)
        }
        navController.navigate(destination){
            if (destination == Content.LearningScript.route){
                popUpTo(Content.GuideScript.route){
                }
            }
            else if (destination == Content.LearningSinging.route){
                popUpTo(Content.GuideScript.route){
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment =  Alignment.Center
        ){
            CountDownItem(
                visible = countValue.value == 3,
                content = "3"
            )
            CountDownItem(
                visible = countValue.value == 2,
                content = "2"
            )
            CountDownItem(
                visible = countValue.value == 1,
                content = "1"
            )
            CountDownItem(
                visible = countValue.value == 0,
                content = "시작!"
            )
        }

    }
}