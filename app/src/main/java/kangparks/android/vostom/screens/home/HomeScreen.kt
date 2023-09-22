package kangparks.android.vostom.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kangparks.android.vostom.Greeting
import kangparks.android.vostom.navigations.Content

@Composable
fun HomeScreen(navController : NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Button(onClick = {navController.navigate(Content.LearningScript.route) }) {
            Text(text ="학습 시작")
        }
        Greeting("홈 화면")
    }
}