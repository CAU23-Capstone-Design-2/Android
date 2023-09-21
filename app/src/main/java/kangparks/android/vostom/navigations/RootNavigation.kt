package kangparks.android.vostom.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun RootNavigation(){
    val navController = rememberNavController()

    // 추후 토큰 조회 로직 구현 예정
    val token = true

    NavHost(navController = navController, startDestination = if(token == null) "auth_graph" else "content_graph"){
        AuthNavigation(navController = navController)
        ContentNavigation(navController = navController)
    }
}