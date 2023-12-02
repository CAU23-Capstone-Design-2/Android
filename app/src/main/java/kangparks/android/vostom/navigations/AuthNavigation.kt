package kangparks.android.vostom.navigations

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kangparks.android.vostom.screens.auth.LoginScreen
import kangparks.android.vostom.viewModel.splash.SplashViewModel

sealed class Auth(val route: String) {
    object Login : Auth(route = "login")
}

fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
    viewModel: SplashViewModel
) {
    Log.d("Test-AuthNavigation", "authNavigation")
    navigation(startDestination = Auth.Login.route, route = Nav.AUTH) {
        composable(route = Auth.Login.route) {
            LoginScreen(
                navHostController = navController,
                splashViewModel =  viewModel
            )
        }
    }
}

