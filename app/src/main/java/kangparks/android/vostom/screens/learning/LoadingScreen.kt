package kangparks.android.vostom.screens.learning

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.components.bottomsheet.CelebrityContentBottomSheet
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.services.LearningStateCheckerService
import kangparks.android.vostom.utils.helper.service.startService
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kangparks.android.vostom.viewModel.learning.LearningStateViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(
    navController : NavHostController,
    celebrityContentViewModel : CelebrityContentViewModel,
    learningStateViewModel : LearningStateViewModel,
    checkRunningService : (serviceClass: Class<*>)-> Boolean
){
    val testBuildString = remember { mutableStateOf("빌드 12-03-16-30") }
    val currentLearningState = learningStateViewModel.currentLearningState.observeAsState(LearningState.Learning)

    val context = LocalContext.current
    val loadingAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("loading.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = loadingAnimation,
        iterations = LottieConstants.IterateForever
    )

    val countValue = remember { mutableIntStateOf(5) }

    LaunchedEffect(key1 = null){
        delay(500)

        val resultIsRunningService = checkRunningService(LearningStateCheckerService::class.java)
        if(!resultIsRunningService){
            startService(context, LearningStateCheckerService::class.java)
        }

        Log.d("[LoadingScreen]", "resultIsRunningService: $resultIsRunningService")

        celebrityContentViewModel.updateCelebrityList(
            context = context
        )
    }

    LaunchedEffect(key1 = currentLearningState.value){
        if(currentLearningState.value == LearningState.AfterLearning){
            navController.navigate(LearningContent.Welcome.route)
        }
    }

//    LaunchedEffect(null){
//        while (countValue.value > 0){
//            delay(1000)
//            countValue.value--
//        }
//
////        navController.navigate(LearningContent.Welcome.route)
//    }

    BackHandler(enabled = true) { } // 뒤로 가기 방지

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            text = testBuildString.value,
            fontSize = 10.sp,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
        )
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
        CelebrityContentBottomSheet(
            celebrityContentViewModel = celebrityContentViewModel
        )
    }

}