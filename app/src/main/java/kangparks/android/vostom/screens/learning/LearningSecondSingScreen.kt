package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content

@Composable
fun LearningSecondSingScreen(navController : NavHostController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            hasBackButton = true,
            backButtonContent = "아전 단계로",
            backButtonAction = { navController.popBackStack()},
            hasMainContent = true,
            mainContent = "좋아하는 노래를 찾아 신나게 불러봐요",
            hasNextButton = true,
            nextButtonContent = "노래 녹음하기",
            nextButtonAction = { navController.navigate(Content.Loading.route) } // 임시 이동
        ){}
    }
}