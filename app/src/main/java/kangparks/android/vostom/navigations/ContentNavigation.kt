package kangparks.android.vostom.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.screens.home.HomeScreen
import kangparks.android.vostom.screens.learning.CountDownScreen
import kangparks.android.vostom.screens.learning.DetailGuideScreen
import kangparks.android.vostom.screens.learning.FinishLearningScriptScreen
import kangparks.android.vostom.screens.learning.FinishLearningSingingScreen
import kangparks.android.vostom.screens.learning.GuideScreen
import kangparks.android.vostom.screens.learning.GuideScriptScreen
import kangparks.android.vostom.screens.learning.GuideSingingScreen
import kangparks.android.vostom.screens.learning.LearningScriptScreen
import kangparks.android.vostom.screens.learning.LearningSingingScreen
import kangparks.android.vostom.screens.learning.LoadingScreen
import kangparks.android.vostom.screens.permission.PermissionGuideScreen
import kangparks.android.vostom.screens.setting.SettingScreen
import kangparks.android.vostom.viewModel.learning.SingingViewModel

sealed class Content(val route : String){
    object Home : Content(route = "home")
    object Guide : Content(route = "guide")
    object DetailGuide : Content(route = "detail_guide")
    object PermissionGuide : Content(route = "permission_guide")
    object GuideScript : Content(route = "guide_script")
    object LearningScript : Content(route = "learning_script")
    object FinishLearningScript : Content(route = "finish_learning_script")
    object GuideSinging : Content(route = "guide_singing")
    object LearningSinging : Content(route = "learning_singing")
    object FinishLearningSinging : Content(route = "finish_learning_singing")
    object CountDown : Content(route = "count_down")
    object Loading : Content(route = "loading")
    object Setting : Content(route = "setting")
}

fun NavGraphBuilder.contentNavigation(
    navController : NavHostController,
){
    val isLearnUserVoice = false
    val singingViewModel = SingingViewModel()

    navigation(
        startDestination =
        if(isLearnUserVoice)Content.Home.route
        else Content.Guide.route,
//        else Content.GuideSinging.route,
        route = Nav.CONTENT){
        composable(Content.Home.route,) { HomeScreen(navController = navController ) }
        composable(Content.Guide.route,){ GuideScreen(navController = navController ) }
        composable(Content.DetailGuide.route) { DetailGuideScreen(navController = navController)}
        composable(Content.PermissionGuide.route) { PermissionGuideScreen(navController = navController) }
        composable(Content.CountDown.route+"/{destination}"){it->
            val destination = it.arguments?.getString("destination") ?: Content.Home.route
            CountDownScreen(
                navController = navController,
                destination = destination
            )
        }
        composable(Content.GuideScript.route) { GuideScriptScreen(navController = navController) }
        composable(Content.LearningScript.route) { LearningScriptScreen(navController = navController) }
        composable(Content.FinishLearningScript.route){ FinishLearningScriptScreen(navController = navController)}
        composable(Content.GuideSinging.route) {
            GuideSingingScreen(
                navController = navController,
                singingViewModel = singingViewModel
            )
        }
        composable(Content.LearningSinging.route){
            LearningSingingScreen(
                navController = navController,
                singingViewModel = singingViewModel
            )
        }
        composable(Content.FinishLearningSinging.route){ FinishLearningSingingScreen(navController = navController)}
        composable(Content.Loading.route) { LoadingScreen(navController = navController) }
        composable(Content.Setting.route){ SettingScreen()}
    }
}