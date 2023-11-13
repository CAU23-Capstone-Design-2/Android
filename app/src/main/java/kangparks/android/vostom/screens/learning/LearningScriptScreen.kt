package kangparks.android.vostom.screens.learning

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.components.content.ScriptContent
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.viewModel.learning.ScriptProviderViewModel
import kangparks.android.vostom.viewModel.recorder.RecordFileViewModel
import kangparks.android.vostom.viewModel.recorder.AudioRecorderViewModel

@Composable
fun LearningScriptScreen(
    navController: NavHostController,
    recordFileViewModel: RecordFileViewModel,
    scriptProvider: ScriptProviderViewModel
) {
    val scrollState = rememberScrollState()

    val recordAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("record.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = recordAnimation,
        iterations = LottieConstants.IterateForever,
    )

    val context = LocalContext.current
    val recorderViewModel = AudioRecorderViewModel(context, context.filesDir)
    val currentScriptID = scriptProvider.getScriptID()

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
        color = MaterialTheme.colorScheme.background
    ) {
        LearningLayoutTemplate(
            backButtonContent = "녹음 다시 하기",
            backButtonAction = {
                recorderViewModel.reset()
                navController.popBackStack()
            },
            mainContent = "사용자의 스크립트를 녹음 중 입니다.",
            nextButtonContent = "스크립트 녹음 완료하기",
            nextButtonAction = {
                recorderViewModel.stop()
//                val recordFile = recorderViewModel.getAudioFile()
                val recordFile = recorderViewModel.getOutputFile()
                if (recordFile != null) {
                    recordFileViewModel.addRecordFile(recordFile)
                    navController.navigate(LearningContent.FinishLearningScript.route)
                }
                else{
                    Toast.makeText(context, "녹음 파일에 문제가 있습니다.\n 다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
                    recorderViewModel.reset()
                    navController.popBackStack()
                }
            },
            nextButtonContainerColor = Color(0xFFFC803B)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .height(140.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    LottieAnimation(
                        composition = recordAnimation,
                        progress = progress,
                        contentScale = ContentScale.FillHeight
                    )
                }
                ScriptContent(
                    scrollState = scrollState,
                    scriptID = currentScriptID
                )
            }

        }
    }
}
