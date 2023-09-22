package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kangparks.android.vostom.Greeting

@Composable
fun LearningScriptScreen(navController : NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("스크립트 학습 화면")
    }
}