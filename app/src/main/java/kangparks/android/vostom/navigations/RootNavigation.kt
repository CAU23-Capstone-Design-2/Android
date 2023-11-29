package kangparks.android.vostom.navigations

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kangparks.android.vostom.screens.player.ContentPlayerScreen
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.content.ContentStoreViewModelFactory
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

object Nav {
    const val AUTH = "auth_graph"
    const val CONTENT = "content_graph"
    const val LEARNING_CONTENT = "learning_content_graph"
    const val HOME_CONTENT = "home_content_graph"
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VostomApp() {
    val navController = rememberNavController()
    val accessToken = getAccessToken(LocalContext.current)

    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navController) }
    ){
        RootNavigation(
            navController = navController,
            token = accessToken
        )
    }

}

@Composable
fun RootNavigation(
    navController: NavHostController,
    token: String?
){
    val contentPlayerViewModel : ContentPlayerViewModel = viewModel()
    val currentGroupViewModel : CurrentGroupViewModel = viewModel()

    val contentStoreViewModel : ContentStoreViewModel = viewModel(
        factory = ContentStoreViewModelFactory(LocalContext.current)
    )

    val curNav = if(token == null) Nav.AUTH else Nav.CONTENT

    NavHost(navController = navController, startDestination = curNav){
        authNavigation(navController = navController)
        contentNavigation(
            navController = navController,
            contentPlayerViewModel = contentPlayerViewModel,
            contentStoreViewModel = contentStoreViewModel,
            currentGroupViewModel = currentGroupViewModel,
        )
    }
    ContentPlayerScreen(
        navController = navController,
        contentPlayerViewModel = contentPlayerViewModel,
    )
}
