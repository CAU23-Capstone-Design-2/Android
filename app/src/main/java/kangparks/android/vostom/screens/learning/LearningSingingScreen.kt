package kangparks.android.vostom.screens.learning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.components.player.YoutubePlayer
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.viewModel.recorder.RecordFileViewModel
import kangparks.android.vostom.viewModel.learning.SingingViewModel

@Composable
fun LearningSingingScreen(
    navController : NavHostController,
    singingViewModel: SingingViewModel,
    recordFileViewModel: RecordFileViewModel
){
    val songItem = singingViewModel.songItem.observeAsState(initial = null)
    val lifecycleOwner = LocalLifecycleOwner.current

    val recordAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("record.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = recordAnimation,
        iterations = LottieConstants.IterateForever,
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF2F2F2F)
    ){
        LearningLayoutTemplate(
            backButtonContent = "녹음 다시 하기",
            backButtonAction = {  },
            mainContent = "사용자의 노래를 녹음 중 입니다.",
            nextButtonContent = "노래 녹음 완료하기",
            nextButtonAction = {
                navController.navigate(Content.FinishLearningSinging.route)
                               },
            nextButtonContainerColor = Color(0xFFFC803B),
            contentHorizontalPadding = 0,
            color = Color.White
        ){
            songItem.value?.let {
                Box {
                    YoutubePlayer(
                        contentId = it.contentUri,
                        lifecycleOwner = lifecycleOwner
                    )
                    Box(
                        modifier = Modifier.fillMaxSize().padding(bottom = 160.dp),
                        contentAlignment = Alignment.BottomCenter
                    ){
                        Box(
                            modifier = Modifier
                                .height(160.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            LottieAnimation(
                                composition = recordAnimation,
                                progress = progress,
                                contentScale = ContentScale.FillHeight
                            )
                        }
                    }
                }
            }
        }

    }
}