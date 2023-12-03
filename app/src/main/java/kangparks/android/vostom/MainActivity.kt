package kangparks.android.vostom

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.navigations.VostomApp
import kangparks.android.vostom.ui.theme.VostomTheme
import kangparks.android.vostom.viewModel.learning.LearningStateViewModel
import kangparks.android.vostom.viewModel.splash.SplashViewModel


class MainActivity : ComponentActivity() {
    private val splashViewModel by viewModels<SplashViewModel>()
    private val learningStateViewModel by viewModels<LearningStateViewModel>()

    private val learningStateReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val resultOfLearningState = intent.getIntExtra("state", 1)
            Log.d("MainActivity", "resultOfLearningState : $resultOfLearningState")

            if(resultOfLearningState == 2){
                learningStateViewModel.setCurrentLearningState(LearningState.AfterLearning)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        cacheDir

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {

                    return when (splashViewModel.complete.value) {
                        true -> {
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            }
        )

        Handler(mainLooper).postDelayed({
            splashViewModel.updateComplete()
        },500)

        LocalBroadcastManager.getInstance(this).registerReceiver(
            learningStateReceiver, IntentFilter("StateOfLearningState")
        );


        setContent {
            VostomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VostomApp(
                        splashViewModel = splashViewModel,
                        learningStateViewModel = learningStateViewModel,
                        checkRunningService = {it ->
                            checkRunningService(it)
                        }
                    )
                }
            }
        }
    }

    private fun checkRunningService(
        serviceClass: Class<*>,
    ) : Boolean{
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}

