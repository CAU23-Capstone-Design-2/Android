package kangparks.android.vostom.navigations

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kangparks.android.vostom.components.navigationBar.BottomNavigationBar

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

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ){
        RootNavigation(navController = navController)
    }

}

@Composable
fun RootNavigation(
    navController: NavHostController = rememberNavController(),
){
//    val token = getAccessToken(context) // 저장된 토큰 불러오기

    val curNav = if(true) Nav.AUTH else Nav.CONTENT

    NavHost(navController = navController, startDestination = curNav){
        authNavigation(navController = navController)
        contentNavigation(
            navController = navController,
        )
    }
}
