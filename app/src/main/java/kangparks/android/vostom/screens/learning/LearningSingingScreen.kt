package kangparks.android.vostom.screens.learning

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.viewModel.recorder.RecordFileViewModel
import kangparks.android.vostom.viewModel.learning.SingingViewModel
import kangparks.android.vostom.viewModel.recorder.AudioRecorderViewModel

@Composable
fun LearningSingingScreen(
    navController: NavHostController,
    singingViewModel: SingingViewModel,
    recordFileViewModel: RecordFileViewModel
) {
    val songItem = singingViewModel.songItem.observeAsState(initial = null)
    val lifecycleOwner = LocalLifecycleOwner.current

    val recordAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("record.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = recordAnimation,
        iterations = LottieConstants.IterateForever,
    )

    val context = LocalContext.current
    val recorderViewModel = AudioRecorderViewModel(context, context.filesDir)

    LaunchedEffect(null) {
        val currentFileName = recordFileViewModel.getCurrentRecordFileName()
        Log.d("currentFileName", "currentFileName: $currentFileName")
        Log.d("size", "size: ${recordFileViewModel.getRecordFileList().size}")
        recorderViewModel.start(fileName = currentFileName)
    }

    DisposableEffect(null) {
        onDispose {
            recorderViewModel.reset()
        }
    }

    BackHandler(enabled = true) { }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF2F2F2F)
    ) {
        LearningLayoutTemplate(
            backButtonContent = "녹음 다시 하기",
            backButtonAction = { },
            mainContent = "사용자의 노래를 녹음 중 입니다.",
            nextButtonContent = "노래 녹음 완료하기",
            nextButtonAction = {
                val recordFile = recorderViewModel.getOutputFile(recordFileViewModel.getCurrentRecordFileName())
                if (recordFile != null){
                    recordFileViewModel.addRecordFile(recordFile)
                    navController.navigate(LearningContent.FinishLearningSinging.route)
                }
                else{
                    Toast.makeText(context, "녹음 파일에 문제가 있습니다.\n 다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
                    recorderViewModel.reset()
                    navController.popBackStack()
                }

            },
            nextButtonContainerColor = Color(0xFFFC803B),
            contentHorizontalPadding = 0,
            color = Color.White
        ) {
            songItem.value?.let {
                Box {
                    YoutubePlayer(
                        contentId = it.contentUri,
                        lifecycleOwner = lifecycleOwner
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 160.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .height(160.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
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