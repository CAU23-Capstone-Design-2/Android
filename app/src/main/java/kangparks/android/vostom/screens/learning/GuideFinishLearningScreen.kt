package kangparks.android.vostom.screens.learning

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.components.dialog.ProceedingDialog
import kangparks.android.vostom.components.dialog.VostomDialog
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.viewModel.recorder.RecordFileViewModel

@Composable
fun GuideFinishLearningScreen(
    navController : NavHostController,
    recordFileViewModel : RecordFileViewModel
) {
    val context = LocalContext.current
    val isAfterUploadFiles = recordFileViewModel.isAfterUploadFiles.observeAsState(initial = false)
    val isProceedingDialogOpen = recordFileViewModel.isProceedingDialogOpen.observeAsState(initial = false)


    val thanksAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("thanks.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = thanksAnimation,
        iterations = LottieConstants.IterateForever,
    )

    LaunchedEffect(null){
        // 음성 파일 업로드 부분
//        val accessToken = getAccessToken(context)
//        recordFileViewModel.uploadRecordFileToServer(accessToken)
    }

    LaunchedEffect(key1 = isAfterUploadFiles.value){
        // 업로드 완료 후 다음 화면으로 이동
        if(isAfterUploadFiles.value){
            navController.navigate(LearningContent.Loading.route)
        }
    }

    BackHandler(enabled = true) { } // 뒤로 가기 방지

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        LearningLayoutTemplate(
            mainContent = "수고했습니다!",
            subContent = "목소리 학습 종료 버튼을 누르면 Vostom이 사용자의 목소리를 학습해서 AI 커버곡을 생성할 수 있도록 준비 할게요!☺️",
            nextButtonContent = "목소리 학습 종료",
            nextButtonAction = {
                recordFileViewModel.uploadRecordFileToServer(context)

                // 임시 이동
//                navController.navigate(LearningContent.Loading.route)
            },
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
    AnimatedVisibility(visible = isProceedingDialogOpen.value) {
        ProceedingDialog(
            onDismissRequest = { },
            content = "사용자의 학습 데이터를\nVostom에 업로드중 입니다.\n잠시만 기다려 주세요!🥰"
        )
    }
}