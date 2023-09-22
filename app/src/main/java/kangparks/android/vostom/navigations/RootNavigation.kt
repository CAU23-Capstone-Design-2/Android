package kangparks.android.vostom.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

object Nav {
    const val AUTH = "auth_graph"
    const val CONTENT = "content_graph"
}

@Composable
fun VostomApp() {
    val navController = rememberNavController()
    RootNavigation(
        navController = navController
    )
}

@Composable
fun RootNavigation(
    navController: NavHostController
){
    // 추후 토큰 조회 로직 구현 예정
    val token = true

    NavHost(navController = navController, startDestination = if(token == null) Nav.AUTH else Nav.CONTENT){
        authNavigation(navController = navController)
        contentNavigation(navController = navController)
    }
}