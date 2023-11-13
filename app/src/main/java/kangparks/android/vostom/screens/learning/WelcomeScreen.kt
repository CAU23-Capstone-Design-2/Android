package kangparks.android.vostom.screens.learning

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Nav

@Composable
fun WelcomeScreen(navController : NavHostController) {
    val completeAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("complete.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = completeAnimation,
        iterations = LottieConstants.IterateForever,
    )

    BackHandler(enabled = true) { } // 뒤로 가기 방지

    Surface {
        LearningLayoutTemplate(
            mainContent = "축하합니다!\n목소리 학습이 완료되었습니다.",
            nextButtonContent = "Vostom 시작하기",
            nextButtonAction = {
                navController.navigate(Nav.HOME_CONTENT)
            }
        ){
            Column{
                Spacer(modifier = Modifier.height(100.dp))
                Box(
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    LottieAnimation(
                        composition = completeAnimation,
                        progress = progress,
                        contentScale = ContentScale.FillHeight
                    )
                }
            }

        }
    }
}