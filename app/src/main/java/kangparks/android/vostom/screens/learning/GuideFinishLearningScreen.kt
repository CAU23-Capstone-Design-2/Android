package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content

@Composable
fun GuideFinishLearningScreen(navController : NavHostController) {
    val thanksAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("thanks.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = thanksAnimation,
        iterations = LottieConstants.IterateForever,
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        LearningLayoutTemplate(
            mainContent = "수고했습니다!",
            subContent = "목소리 학습 종료 버튼을 누르면 Vostom이 사용자의 목소리를 학습해서 AI 커버곡을 생성할 수 있도록 준비 할게요!☺️",
            nextButtonContent = "목소리 학습 종료",
            nextButtonAction = {
                navController.navigate(Content.Loading.route)
            }, // 임시 이동
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
                Spacer(modifier = Modifier.height(60.dp))
                Box(
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    LottieAnimation(
                        composition = thanksAnimation,
                        progress = progress,
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
        }
    }
}