package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kangparks.android.vostom.Greeting
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content

@Composable
fun LearningScriptScreen(navController : NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            hasBackButton = true,
            backButtonContent = "아전 단계로",
            backButtonAction = { navController.popBackStack()},
            hasMainContent = true,
            mainContent = "다음 제공되는 스크립트를 따라 읽어보세요.",
            hasNextButton = true,
            nextButtonContent = "스크립트 녹음하기",
            nextButtonAction = { navController.navigate(Content.LearningFirstSing.route) } // 임시 이동
        ){
            Text(text = "스크립트 스크립트")
        }
    }
}