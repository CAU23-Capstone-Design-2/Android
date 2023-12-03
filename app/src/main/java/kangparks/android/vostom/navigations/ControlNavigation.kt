package kangparks.android.vostom.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.screens.control.ControlScreen
import kangparks.android.vostom.viewModel.splash.SplashViewModel

sealed class Control(val route : String){
    object Main : Control(route = "main")
}

fun NavGraphBuilder.controlNavigation(
    navController: NavHostController,
    splashViewModel: SplashViewModel
){
    navigation(startDestination = Control.Main.route, route = Nav.CONTROL){
        composable(route = Control.Main.route){
            ControlScreen(
                navController = navController,
                splashViewModel = splashViewModel
            )
        }
    }
}