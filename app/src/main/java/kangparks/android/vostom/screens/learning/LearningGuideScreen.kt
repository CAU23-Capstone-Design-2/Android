package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.Greeting
import kangparks.android.vostom.components.buttonsheet.BottomSheet
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningGuideScreen(navController : NavHostController){
    val singingAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("singing.json"))
    val progress by animateLottieCompositionAsState(
        composition = singingAnimation,
        iterations = LottieConstants.IterateForever,
    )

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {  true},

    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            hasMainContent = true,
            mainContent = "내 목소리에 어울리는 노래를 찾기 위해 목소리 학습하는 과정이 필요해요!",
            hasSubContent = true,
            subContent = "조용한 환경에서 진행하면 학습 정확도가 높아집니다.\uD83D\uDE06",
            hasNextButton = true,
            nextButtonContent = "나의 목소리 학습 시작하기",
            nextButtonAction = {
                navController.navigate(Content.LearningPitch.route)
            },
            nextButtonBottomPaddingValue = 60
        ){
            Box(modifier = Modifier
                .height(250.dp)
                .width(250.dp)) {
                Box(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    LottieAnimation(
                        composition = singingAnimation,
                        progress = { progress },
                        contentScale = ContentScale.FillHeight,
                    )
                }}

        }
        BottomSheet()
    }
}