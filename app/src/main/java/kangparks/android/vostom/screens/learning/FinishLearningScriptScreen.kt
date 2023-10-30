package kangparks.android.vostom.screens.learning

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kangparks.android.vostom.components.template.LearningLayoutTemplate
import kangparks.android.vostom.navigations.Content
import kangparks.android.vostom.viewModel.learning.ScriptProviderViewModel

@Composable
fun FinishLearningScriptScreen(
    navController: NavHostController,
    scriptProvider: ScriptProviderViewModel
) {
    val learningAnimation by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("learning.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = learningAnimation,
        iterations = LottieConstants.IterateForever,
    )
    val hasNextScript = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = null, block = {
        hasNextScript.value = scriptProvider.hasNext()
    })

    BackHandler(enabled = true) { }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AnimatedVisibility(visible = hasNextScript.value, enter = fadeIn(), exit = fadeOut()) {
            LearningLayoutTemplate(
                mainContent = "좋아요! 목소리 학습하는데 도움이 되고 있어요~",
                subAnnotatedString = buildAnnotatedString {
                    append("정확한 학습을 위해서 더 많은 사용자의 목소리 데이터가 필요합니다. ")
                    withStyle(
                        SpanStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("추가 스크립트 녹음하기")
                    }
                    append(" 버튼을 통해 ")
                    withStyle(
                        SpanStyle(
                            fontSize = 15.sp,
                            color = Color(0xFF8B62FF),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append("Vostom")
                    }
                    append("이 더 정확한 사용자 목소리 학습하는데 도움을 주세요! \uD83E\uDD7A")
                },
                nextButtonContent = "추가 스크립트 녹음하기",
                nextButtonAction = {
                    navController.navigate(Content.GuideScript.route)
                },
                othersOptionButtonContent = "아니요! 다음 단계 진행 할래요.",
                othersOptionButtonAction = {
                    navController.navigate(Content.GuideSinging.route)
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    Box(
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        LottieAnimation(
                            composition = learningAnimation,
                            progress = progress,
                            contentScale = ContentScale.FillHeight
                        )
                    }
                }
            }
        }
        AnimatedVisibility(visible = !hasNextScript.value, enter = fadeIn(), exit = fadeOut()) {
            LearningLayoutTemplate(
                mainContent = "좋아요! 목소리 학습하는데 도움이 되고 있어요~",
                subContent = "추가 스크립트가 없습니다. 다음 단계로 진행해주세요.",
                nextButtonContent = "다음 단계 진행",
                nextButtonAction = {
                    navController.navigate(Content.GuideSinging.route)
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    Box(
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        LottieAnimation(
                            composition = learningAnimation,
                            progress = progress,
                            contentScale = ContentScale.FillHeight
                        )
                    }
                }
            }
        }
    }
}