package kangparks.android.vostom.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.screens.home.HomeScreen
import kangparks.android.vostom.screens.learning.LearningCountScreen
import kangparks.android.vostom.screens.learning.LearningFirstSingScreen
import kangparks.android.vostom.screens.learning.LearningGuideScreen
import kangparks.android.vostom.screens.learning.LearningPitchScreen
import kangparks.android.vostom.screens.learning.LearningScriptScreen
import kangparks.android.vostom.screens.learning.LearningSecondSingScreen
import kangparks.android.vostom.screens.learning.LoadingScreen
import kangparks.android.vostom.screens.permission.PermissionGuideScreen
import kangparks.android.vostom.screens.setting.SettingScreen

sealed class Content(val route : String){
    object Home : Content(route = "home")
    object LearningGuide : Content(route = "learning_guide")
    object LearningPitch : Content(route = "learning_pitch")
    object LearningScript : Content(route = "learning_script")
    object LearningFirstSing : Content(route = "learning_first_sing")
    object LearningSecondSing : Content(route = "learning_second_sing")
    object LearningCount : Content(route = "learning_count")
    object Loading : Content(route = "loading")
    object PermissionGuide : Content(route = "permission_guide")
    object Setting : Content(route = "setting")
}

fun NavGraphBuilder.contentNavigation(navController : NavHostController){
    val isLearnUserVoice = false

    navigation(startDestination = if(isLearnUserVoice)Content.Home.route else Content.LearningGuide.route, route = Nav.CONTENT){
        composable(Content.Home.route,) { HomeScreen(navController = navController ) }
        composable(Content.LearningGuide.route,){ LearningGuideScreen(navController = navController ) }
        composable(Content.LearningPitch.route) { LearningPitchScreen(navController = navController ) }
        composable(Content.LearningScript.route) { LearningScriptScreen(navController = navController) }
        composable(Content.LearningFirstSing.route) { LearningFirstSingScreen(navController = navController) }
        composable(Content.LearningSecondSing.route) { LearningSecondSingScreen(navController = navController) }
        composable(Content.LearningCount.route){ LearningCountScreen(navController = navController) }
        composable(Content.Loading.route) { LoadingScreen(navController = navController) }
        composable(Content.PermissionGuide.route) { PermissionGuideScreen(navController = navController) }
        composable(Content.Setting.route){ SettingScreen()}
    }
}