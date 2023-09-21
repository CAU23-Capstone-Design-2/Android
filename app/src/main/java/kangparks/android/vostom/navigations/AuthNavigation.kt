package kangparks.android.vostom.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kangparks.android.vostom.screens.auth.LoginScreen

fun NavGraphBuilder.AuthNavigation(navController : NavHostController){
    navigation(startDestination = "login", route = "auth_graph"){
        composable("login") { LoginScreen() }
    }
}