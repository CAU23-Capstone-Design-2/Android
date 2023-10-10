package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import kangparks.android.vostom.components.bottomsheet.OthersContentBottomSheet
import kangparks.android.vostom.components.template.LearningLayoutTemplate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(navController : NavHostController){
    val loadingAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("loading.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = loadingAnimation,
        iterations = LottieConstants.IterateForever
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            mainContent = "Vostom에서 사용자의 목소리를 열심히 학습하고 있어요!",
            subContent = "작업이 완료되면 알려드릴게요.\uD83D\uDE0E",
            contentModifier = Modifier.padding(bottom = 140.dp),
            contentAlignment = Alignment.Center
        ){
            Box(modifier = Modifier
                .height(200.dp)
                .width(200.dp)) {
                LottieAnimation(
                    composition = loadingAnimation,
                    progress = { progress },
                    contentScale = ContentScale.FillHeight,
                )
            }
        }
        OthersContentBottomSheet()
    }

}