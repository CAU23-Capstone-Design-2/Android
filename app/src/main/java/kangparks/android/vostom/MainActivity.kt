package kangparks.android.vostom

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import kangparks.android.vostom.navigations.VostomApp
import kangparks.android.vostom.ui.theme.VostomTheme
import kangparks.android.vostom.utils.helper.learning.checkCurrentUserLearningState
import kangparks.android.vostom.utils.networks.learning.getLearningState
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.splash.RequestState
import kangparks.android.vostom.viewModel.splash.SplashViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        cacheDir

        // SplashScreen 동안 토큰 및 사용자 학습 상태 확인
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
//                    return if (viewModel.accessToken.value == null) { // 로그인 안됨
////                        Log.d("Test-SplashScreen", "${viewModel.accessToken.value}")
////                        Log.d("Test-SplashScreen", "token is null")
////                        content.viewTreeObserver.removeOnPreDrawListener(this)
//                        false
//                    } else{
//                        when(viewModel.isReceivedRequestLearningState.value!!.state){
//                            RequestState.BeforeRequest.state -> {
//                                Log.d("Test-SplashScreen", "BeforeRequest")
//                                viewModel.getCurrentLearningState()
//                                false
//                            }
//                            RequestState.Requesting.state -> {
//                                Log.d("Test-SplashScreen", "Requesting")
//                                false
//                            }
//                            RequestState.AfterRequest.state -> {
//                                Log.d("Test-SplashScreen", "AfterRequest")
//                                viewModel.updateComplete()
//                                content.viewTreeObserver.removeOnPreDrawListener(this)
//
////                                Handler(mainLooper).postDelayed({
////
////                                    false
////                                },3000)
//                                true
//                            }
//                            else -> {
//                                false
//                            }
//                        }
//                    }

                    return when (viewModel.isReceivedRequestLearningState.value!!.state) {
                        RequestState.BeforeRequest.state -> {
                            Log.d("Test-SplashScreen", "BeforeRequest")
                            viewModel.getCurrentLearningState()
                            false
                        }

                        RequestState.Requesting.state -> {
                            Log.d("Test-SplashScreen", "Requesting")
                            false
                        }

                        RequestState.AfterRequest.state -> {
                            Log.d("Test-SplashScreen", "AfterRequest")
                            viewModel.updateComplete()
                            content.viewTreeObserver.removeOnPreDrawListener(this)

//                                Handler(mainLooper).postDelayed({
//
//                                    false
//                                },3000)
                            true
                        }

                        else -> {
                            false
                        }
                    }

//                    return if (viewModel.complete.value == true) {
//                        content.viewTreeObserver.removeOnPreDrawListener(this)
//                        true
//
//                    } else {
////                        false
//                        if (viewModel.accessToken.value == null) { // 로그인 안됨
//                            Log.d("Test-SplashScreen", "${viewModel.accessToken.value}")
//                            Log.d("Test-SplashScreen", "token is null")
//                            content.viewTreeObserver.removeOnPreDrawListener(this)
//                            true
//                        } else{
//                            Log.d("Test-SplashScreen", "${viewModel.accessToken.value}")
//                            Log.d("Test-SplashScreen", "token is not null")
//                            when(viewModel.isReceivedRequestLearningState.value!!.state){
//                                RequestState.BeforeRequest.state -> {
//                                    Log.d("Test-SplashScreen", "BeforeRequest")
//                                    viewModel.getCurrentLearningState()
//                                    false
//                                }
//                                RequestState.Requesting.state -> {
//                                    Log.d("Test-SplashScreen", "Requesting")
//                                    false
//                                }
//                                RequestState.AfterRequest.state -> {
//                                    Log.d("Test-SplashScreen", "AfterRequest")
////                                    content.viewTreeObserver.removeOnPreDrawListener(this)
////                                    Handler(mainLooper).postDelayed({
////                                        viewModel.updateComplete()
////                                        false
////                                    },3000)
//                                    false
//                                }
//                                else -> {
//                                    false
//                                }
//                            }
//                        }
//                        false
//                    }
                }
            }
        )

//        // 1초 후 뷰 그리면서 SplashScreen 종료
//        Handler(mainLooper).postDelayed({
//            viewModel.updateComplete()
//        },3000)


        setContent {
            val accessToken = viewModel.accessToken.observeAsState()
            val learningState = viewModel.currentLearningState.observeAsState()
            val complete = viewModel.complete.observeAsState()

            VostomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedVisibility(complete.value == false) {
                        Box {

                        }
                    }
                    AnimatedVisibility(visible = complete.value == true) {
                        VostomApp(
                            viewModel,
                            accessToken = accessToken.value,
                            currentLearningState = learningState.value
//                        accessToken = viewModel.accessToken.value,
//                        currentLearningState = viewModel.currentLearningState.value
                        )
                    }

                }
            }
        }
    }
}

