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

fun NavGraphBuilder.ContentNavigation(navController : NavHostController){
    navigation(startDestination = "home", route = "content_graph"){
        composable("home") { HomeScreen() }
        composable("learning_guide"){ LearningGuideScreen() }
        composable("learning_pitch") { LearningPitchScreen() }
        composable("learning_script") { LearningScriptScreen() }
        composable("learning_sing") { LearningSingScreen() }
        composable("loading") { LoadingScreen() }
    }
}