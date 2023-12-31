package kangparks.android.vostom.screens.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import kangparks.android.vostom.BuildConfig
import kangparks.android.vostom.R
import kangparks.android.vostom.components.player.VideoBackground
import kangparks.android.vostom.components.button.RoundedButton
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.navigations.LearningContent
import kangparks.android.vostom.navigations.Nav
import kangparks.android.vostom.utils.helper.auth.withKakaoLogin
import kangparks.android.vostom.utils.media.getMediaItem
import kangparks.android.vostom.viewModel.player.VideoBackgroundViewModel
import kangparks.android.vostom.viewModel.splash.RequestState
import kangparks.android.vostom.viewModel.splash.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    splashViewModel : SplashViewModel
){
    val testBuildString = remember { mutableStateOf("빌드 12-02-16-00") }

    val kakaoAppKey = BuildConfig.kakao_api_key
    val doubleBackToExitPressedOnce = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val requestState = splashViewModel.isReceivedRequestLearningState.observeAsState(RequestState.BeforeRequest)
    val currentLearningState = splashViewModel.currentLearningState.observeAsState(null)

    val mediaItem = getMediaItem(context, "login_background", "raw")
    val exoPlayer = remember(context){
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            playWhenReady = true
            prepare()
            volume = 0f
            repeatMode = Player.REPEAT_MODE_ALL
        }
    }

    val viewModel = remember {
        VideoBackgroundViewModel(exoPlayer)
    }

    LaunchedEffect(key1 = requestState.value){
        if(requestState.value == RequestState.AfterRequest){
            if(currentLearningState.value == LearningState.AfterLearning){
                navHostController.navigate(HomeContent.Home.route){
                    navHostController.popBackStack()
                }
            }
            else if(currentLearningState.value == LearningState.BeforeLearning){
                navHostController.navigate(LearningContent.Guide.route){
                    navHostController.popBackStack()
                }
            }
            else if(currentLearningState.value == LearningState.Learning){
                navHostController.navigate(LearningContent.Loading.route){
                    navHostController.popBackStack()
                }
            }
            else{
                splashViewModel.reset()
                navHostController.navigate(Nav.AUTH){
                    navHostController.popBackStack()
                }
            }
        }
    }

    BackHandler(enabled = true) {
        if(doubleBackToExitPressedOnce.value){
            (context as Activity).finish()
        }else{
            doubleBackToExitPressedOnce.value = true
            Toast.makeText(context, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()

            coroutineScope.launch {
                delay(2000)
                doubleBackToExitPressedOnce.value = false
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    )
    {
        Box {
            Text(
                text = testBuildString.value,
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.statusBars)
            )
            VideoBackground(
                viewModel = viewModel,
                exoPlayer = exoPlayer
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 58.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ){
                    Image(
                        painter = painterResource(id = R.drawable.screen_title),
                        contentDescription = "vostom title",
                        modifier = Modifier
                            .height(36.dp),
                        contentScale = ContentScale.FillHeight,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.screen_subtitle),
                        contentDescription = "vostom subtitle",
                        modifier = Modifier
                            .height(16.dp),
                        contentScale = ContentScale.FillHeight,
                    )
                    Spacer(modifier = Modifier.height(300.dp))
                }
                Column(modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxSize(),verticalArrangement = Arrangement.Bottom){

                    val applicationContext = LocalContext.current as ComponentActivity
                    RoundedButton(
                        text = "Vostom 시작하기",
                        onClick = {
                            withKakaoLogin(
                                appKey = kakaoAppKey,
                                context = applicationContext,
                                navController = navHostController,
                                coroutineScope = coroutineScope,
                                exoPlayer = exoPlayer,
                                viewModel = splashViewModel
                            )
                        }
                    )
                }
            }
        }
    }
}