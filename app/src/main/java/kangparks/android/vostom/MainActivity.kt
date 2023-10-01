package kangparks.android.vostom

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
//import android.animation.ObjectAnimator
//import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
//import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import kangparks.android.vostom.navigations.VostomApp
import kangparks.android.vostom.ui.theme.VostomTheme
import kangparks.android.vostom.viewModel.splash.SplashViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        // SplashScreen 시간 조절을 위한 뷰 그리기 일시 중단
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (viewModel.complete.value == true) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                    return false
                }
            }
        )

        // 1초 후 뷰 그리면서 SplashScreen 종료
        Handler(mainLooper).postDelayed({
            viewModel.updateComplete()
        },1000)


        setContent {
            VostomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VostomApp()
                }
            }
        }
    }


}

 @Composable
 fun Greeting(name: String) {
     Text(text = "Hello $name!")
 }

 @Preview(showBackground = true)
 @Composable
 fun DefaultPreview() {
     VostomTheme {
         Greeting("Android")
     }
 }

