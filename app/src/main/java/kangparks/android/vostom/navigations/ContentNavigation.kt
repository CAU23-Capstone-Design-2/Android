package kangparks.android.vostom.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kangparks.android.vostom.screens.home.HomeScreen
import kangparks.android.vostom.screens.learning.LearningGuideScreen
import kangparks.android.vostom.screens.learning.LearningPitchScreen
import kangparks.android.vostom.screens.learning.LearningScriptScreen
import kangparks.android.vostom.screens.learning.LearningSingScreen
import kangparks.android.vostom.screens.learning.LoadingScreen

sealed class Content(val route : String){
    object Home : Content(route = "home")
    object LearningGuide : Content(route = "learning_guide")
    object LearningPitch : Content(route = "learning_pitch")
    object LearningScript : Content(route = "learning_script")
    object LearningSing : Content(route = "learning_sing")
    object Loading : Content(route = "loading")
}

fun NavGraphBuilder.contentNavigation(navController : NavHostController){
    navigation(startDestination = Content.Home.route, route = Nav.CONTENT){
        composable(Content.Home.route) { HomeScreen() }
        composable(Content.LearningGuide.route){ LearningGuideScreen() }
        composable(Content.LearningPitch.route) { LearningPitchScreen() }
        composable(Content.LearningScript.route) { LearningScriptScreen() }
        composable(Content.LearningSing.route) { LearningSingScreen() }
        composable(Content.Loading.route) { LoadingScreen() }
    }
}