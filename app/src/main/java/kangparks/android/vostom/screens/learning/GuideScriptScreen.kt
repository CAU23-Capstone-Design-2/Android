package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.content.ScriptContent
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.viewModel.learning.ScriptProviderViewModel

@Composable
fun GuideScriptScreen(
    navController: NavHostController,
    scriptProvider: ScriptProviderViewModel
) {
    val scrollState = rememberScrollState()
    val currentScriptID = scriptProvider.getScriptID()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            backButtonContent = "아전 단계로",
            backButtonAction = { navController.popBackStack() },
            mainContent = "다음 제공되는 스크립트를 따라 읽어보세요.",
            subContent = "예상 소요시간 : 3분~5분",
            nextButtonContent = "스크립트 녹음하기",
            nextButtonAction = { navController.navigate(LearningContent.CountDown.route+"/${LearningContent.LearningScript.route}") }
        ) {
            ScriptContent(
                scrollState = scrollState,
                scriptID = currentScriptID
            )
        }
    }
}