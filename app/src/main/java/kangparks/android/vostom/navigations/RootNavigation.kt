package kangparks.android.vostom.navigations

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.screens.player.ContentPlayerScreen
import kangparks.android.vostom.utils.helper.learning.checkCurrentUserLearningState
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.content.ContentStoreViewModelFactory
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel
import kangparks.android.vostom.viewModel.splash.SplashViewModel

object Nav {
    const val AUTH = "auth_graph"
    const val CONTENT = "content_graph"
    const val LEARNING_CONTENT = "learning_content_graph"
    const val HOME_CONTENT = "home_content_graph"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VostomApp(
    viewModel : SplashViewModel,
    accessToken: String?,
    currentLearningState : LearningState?

) {

//    val accessToken = viewModel.accessToken.observeAsState().value
//    val currentLearningState = viewModel.currentLearningState.observeAsState().value

    val navController = rememberNavController()
//    val accessToken = getAccessToken(LocalContext.current)
//    val currentLearningState = checkCurrentUserLearningState(accessToken)
//    Log.d("Test-getLearningState", "currentLearningState : $currentLearningState")

    Scaffold{
        RootNavigation(
            viewModel = viewModel,
            navController = navController,
            token = accessToken,
            learningState = currentLearningState
        )
    }

}

@Composable
fun RootNavigation(
    viewModel : SplashViewModel,
    navController: NavHostController,
    token: String?,
    learningState : LearningState?
){
    val contentPlayerViewModel : ContentPlayerViewModel = viewModel()
    val currentGroupViewModel : CurrentGroupViewModel = viewModel()

    val contentStoreViewModel : ContentStoreViewModel = viewModel(
        factory = ContentStoreViewModelFactory(LocalContext.current)
    )

    Log.d("Test-RootNavigation", "token : $token")
    val curNav = if(token == null) Nav.AUTH else Nav.CONTENT
    Log.d("Test-RootNavigation", "curNav : $curNav")

    NavHost(navController = navController, startDestination = curNav){
        authNavigation(
            navController = navController,
            viewModel = viewModel
        )
        contentNavigation(
            navController = navController,
            contentPlayerViewModel = contentPlayerViewModel,
            contentStoreViewModel = contentStoreViewModel,
            currentGroupViewModel = currentGroupViewModel,
            learningState = learningState
        )
    }
    ContentPlayerScreen(
        navController = navController,
        contentPlayerViewModel = contentPlayerViewModel,
    )
}
