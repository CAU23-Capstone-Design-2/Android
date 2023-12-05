package kangparks.android.vostom.navigations

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.screens.player.ContentPlayerScreen
import kangparks.android.vostom.utils.helper.learning.checkCurrentUserLearningState
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.content.ContentStoreViewModelFactory
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.learning.LearningStateViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kangparks.android.vostom.viewModel.splash.SplashViewModel

object Nav {
    const val CONTROL = "control_graph"
    const val AUTH = "auth_graph"
    const val CONTENT = "content_graph"
    const val LEARNING_CONTENT = "learning_content_graph"
    const val HOME_CONTENT = "home_content_graph"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VostomApp(
    splashViewModel : SplashViewModel,
    learningStateViewModel : LearningStateViewModel,
    checkRunningService : (serviceClass: Class<*>)-> Boolean
) {

    val navController = rememberNavController()

    Scaffold{
        RootNavigation(
            splashViewModel = splashViewModel,
            learningStateViewModel = learningStateViewModel,
            navController = navController,
            checkRunningService = checkRunningService
        )
    }

}

@Composable
fun RootNavigation(
    splashViewModel : SplashViewModel,
    learningStateViewModel : LearningStateViewModel,
    navController: NavHostController,
    checkRunningService : (serviceClass: Class<*>)-> Boolean
){
    val context = LocalContext.current

    val contentPlayerViewModel : ContentPlayerViewModel = viewModel()
    val currentGroupViewModel : CurrentGroupViewModel = viewModel()

    val contentStoreViewModel : ContentStoreViewModel = viewModel(
        factory = ContentStoreViewModelFactory(LocalContext.current)
    )

    NavHost(navController = navController, startDestination = Nav.CONTROL){
        controlNavigation(
            navController = navController,
            splashViewModel = splashViewModel
        )
        authNavigation(
            navController = navController,
            splashViewModel = splashViewModel
        )
        contentNavigation(
            navController = navController,
            context = context,
            contentPlayerViewModel = contentPlayerViewModel,
            contentStoreViewModel = contentStoreViewModel,
            currentGroupViewModel = currentGroupViewModel,
            learningStateViewModel = learningStateViewModel,
            checkRunningService = checkRunningService
        )
    }
    ContentPlayerScreen(
        navController = navController,
        contentStoreViewModel = contentStoreViewModel,
        contentPlayerViewModel = contentPlayerViewModel,
    )
}
