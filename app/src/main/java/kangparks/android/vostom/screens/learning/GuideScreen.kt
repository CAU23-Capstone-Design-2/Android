package kangparks.android.vostom.screens.learning

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.bottomsheet.CelebrityContentBottomSheet
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideScreen(
    navController: NavHostController,
    celebrityContentViewModel : CelebrityContentViewModel
) {
    val testBuildString = remember { mutableStateOf("빌드 12-05-22-00") }

    val singingAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("singing.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = singingAnimation,
        iterations = LottieConstants.IterateForever,
    )
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val doubleBackToExitPressedOnce = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(key1 = null){
        celebrityContentViewModel.updateCelebrityList(
            context = context
        )
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    LaunchedEffect(key1 = bottomSheetScaffoldState.bottomSheetState.currentValue ){
        if(bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Hidden){
            coroutineScope.launch {
                bottomSheetScaffoldState.bottomSheetState.show()
            }
        }
    }

    BackHandler(enabled = true) {
        if (doubleBackToExitPressedOnce.value) {
            (context as Activity).finish()
        } else {
            doubleBackToExitPressedOnce.value = true
            Toast.makeText(context, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()

            coroutineScope.launch {
                delay(2000)
                doubleBackToExitPressedOnce.value = false
            }
        }
    }

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
            mainContent = "내 목소리에 어울리는 노래를 찾기 위해 목소리 학습을 진행해주세요.",
            subContent = "조용한 환경에서 진행하면 학습 정확도가 높아집니다!\uD83D\uDE06",
            nextButtonContent = "나의 목소리 학습 시작하기",
            nextButtonAction = {
                celebrityContentViewModel.resetToCurrentView()
                navController.navigate(LearningContent.DetailGuide.route)
            },
            nextButtonBottomPaddingValue = 60,
        ) {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
            ) {
                LottieAnimation(
                    composition = singingAnimation,
                    progress = { progress },
                    contentScale = ContentScale.FillHeight,
                )
            }
        }
        CelebrityContentBottomSheet(
            bottomSheetScaffoldState = bottomSheetScaffoldState,
            celebrityContentViewModel = celebrityContentViewModel
        )
    }
}