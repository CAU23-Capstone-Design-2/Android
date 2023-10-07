package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.canvas.VoiceMeasureLine
import kangparks.android.vostom.components.player.MusicPlayer
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content

@Composable
fun LearningPitchScreen(navController : NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            backButtonContent = "처음으로",
            backButtonAction = { navController.popBackStack()},
            mainContent = "예시처럼 내 목소리의 음역대를 측정해보세요.",
            nextButtonContent = "음역대 측정하기",
            nextButtonAction = { navController.navigate(Content.LearningScript.route) } // 임시 이동
        ){
            MusicPlayer()
//            VoiceMeasureLine()
        }
    }
}