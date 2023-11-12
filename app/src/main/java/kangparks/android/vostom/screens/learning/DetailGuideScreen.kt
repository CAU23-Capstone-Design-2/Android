package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kangparks.android.vostom.components.content.GuideContent
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.utils.helper.permission.getResultOfCurrentPermissions

@Composable
fun DetailGuideScreen(navController: NavHostController){
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        LearningLayoutTemplate(
            backButtonContent = "이전",
            backButtonAction = { navController.popBackStack()},
            mainContent = "목소리 학습을 위해 다음 단계들을 수행합니다.",
            subContent = "소요 시간 : 최소 10분 이상",
            nextButtonContent = "학습 시작",
            nextButtonAction = {
                if (getResultOfCurrentPermissions(context)) {
                    navController.navigate(LearningContent.GuideScript.route)
                } else {
                    navController.navigate(LearningContent.PermissionGuide.route)
                }
            }
        ){
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                GuideContent(
                    title = "1. 스크립트 녹음",
                    content = "제공되는 스크립트를 따라 읽으면서 녹음하는 과정입니다. 평소 나의 목소리를 학습하는데 도움이 됩니다. \uD83D\uDE01"
                )
                GuideContent(
                    title = "2. 노래 녹음",
                    content = "원하는 노래를 따라 부르면서 녹음하는 과정입니다. 노래 부르는 나의 목소리를 학습하는데 도움이 됩니다. \uD83E\uDD73",
                )
            }
        }
    }
}