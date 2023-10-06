package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kangparks.android.vostom.Greeting
import kangparks.android.vostom.components.buttonsheet.BottomSheet
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content

@Composable
fun LoadingScreen(navController : NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            hasMainContent = true,
            mainContent = "Vostom에서 사용자의 목소리를\n열심히 학습하고 있어요",
            hasSubContent = true,
            subContent = "작업이 완료되면 알려드릴게요!",
            hasNextButton = false,
            nextButtonAction = {
                navController.navigate(Content.LearningPitch.route)
            },
            nextButtonBottomPaddingValue = 60
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Greeting("로티에서 로딩중 에니메이션 찾아서 넣기!")
            }
        }
        BottomSheet()
    }
}