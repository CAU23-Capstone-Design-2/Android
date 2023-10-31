package kangparks.android.vostom.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Face
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.screens.group.BuildGroupScreen
import kangparks.android.vostom.screens.group.GroupListScreen
import kangparks.android.vostom.screens.home.HomeScreen
import kangparks.android.vostom.screens.learning.AddFileScreen
import kangparks.android.vostom.screens.learning.CountDownScreen
import kangparks.android.vostom.screens.learning.DetailGuideScreen
import kangparks.android.vostom.screens.learning.FinishLearningScriptScreen
import kangparks.android.vostom.screens.learning.FinishLearningSingingScreen
import kangparks.android.vostom.screens.learning.GuideFinishLearningScreen
import kangparks.android.vostom.screens.learning.GuideScreen
import kangparks.android.vostom.screens.learning.GuideScriptScreen
import kangparks.android.vostom.screens.learning.GuideSingingScreen
import kangparks.android.vostom.screens.learning.LearningScriptScreen
import kangparks.android.vostom.screens.learning.LearningSingingScreen
import kangparks.android.vostom.screens.learning.LoadingScreen
import kangparks.android.vostom.screens.learning.WelcomeScreen
import kangparks.android.vostom.screens.permission.PermissionGuideScreen
import kangparks.android.vostom.screens.profile.ProfileScreen
import kangparks.android.vostom.viewModel.learning.ScriptProviderViewModel
import kangparks.android.vostom.viewModel.recorder.RecordFileViewModel
import kangparks.android.vostom.viewModel.learning.SingingViewModel

sealed class Content(val route: String) {
    object Home : Content(route = "home")
    object GroupList : Content(route = "group_list")
    object Profile : Content(route = "profile")
    object Guide : Content(route = "guide")
    object DetailGuide : Content(route = "detail_guide")
    object PermissionGuide : Content(route = "permission_guide")
    object GuideScript : Content(route = "guide_script")
    object LearningScript : Content(route = "learning_script")
    object FinishLearningScript : Content(route = "finish_learning_script")
    object GuideSinging : Content(route = "guide_singing")
    object LearningSinging : Content(route = "learning_singing")
    object FinishLearningSinging : Content(route = "finish_learning_singing")
    object AddFile : Content(route = "add_file")
    object GuideFinishLearning : Content(route = "guide_finish_learning")
    object CountDown : Content(route = "count_down")
    object Loading : Content(route = "loading")
    object Welcome : Content(route = "welcome")
    object BuildGroup : Content(route = "build_group")

}

sealed class BottomBarContent(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Home : BottomBarContent(
        route = "home",
        icon = Icons.Filled.Home,
        title = "홈"
    )

    object GroupList : BottomBarContent(
        route = "group_list",
        icon = Icons.Rounded.Face,
        title = "그룹"
    )

    object Profile : BottomBarContent(
        route = "profile",
        icon = Icons.Rounded.AccountCircle,
        title = "프로필"
    )
}

fun NavGraphBuilder.contentNavigation(
    navController: NavHostController,
) {
    val isLearnUserVoice = false
    val singingViewModel = SingingViewModel()
    val recordFileViewModel = RecordFileViewModel()
    val scriptProvider = ScriptProviderViewModel()

    navigation(
        startDestination =
        if (isLearnUserVoice) Content.Home.route
//        else Content.Home.route,
//        else Content.Guide.route,
//        else Content.GuideSinging.route,
                else Content.AddFile.route,
        route = Nav.CONTENT
    ) {
        composable(Content.Home.route) { HomeScreen(navController = navController) }
        composable(Content.GroupList.route) { GroupListScreen(navController = navController) }
        composable(Content.Profile.route) { ProfileScreen(navController = navController) }
        composable(Content.Guide.route) { GuideScreen(navController = navController) }
        composable(Content.DetailGuide.route) { DetailGuideScreen(navController = navController) }
        composable(Content.PermissionGuide.route) { PermissionGuideScreen(navController = navController) }
        composable(Content.CountDown.route + "/{destination}") { it ->
            val destination = it.arguments?.getString("destination") ?: Content.Home.route
            CountDownScreen(
                navController = navController,
                destination = destination
            )
        }
        composable(Content.GuideScript.route) {
            GuideScriptScreen(
                navController = navController,
                scriptProvider = scriptProvider
            )
        }
        composable(Content.LearningScript.route) {
            LearningScriptScreen(
                navController = navController,
                recordFileViewModel = recordFileViewModel,
                scriptProvider = scriptProvider
            )
        }
        composable(Content.FinishLearningScript.route) {
            FinishLearningScriptScreen(
                navController = navController,
                scriptProvider = scriptProvider
            )
        }
        composable(Content.GuideSinging.route) {
            GuideSingingScreen(
                navController = navController,
                singingViewModel = singingViewModel
            )
        }
        composable(Content.LearningSinging.route) {
            LearningSingingScreen(
                navController = navController,
                singingViewModel = singingViewModel,
                recordFileViewModel = recordFileViewModel
            )
        }
        composable(Content.FinishLearningSinging.route) { FinishLearningSingingScreen(navController = navController) }
        composable(Content.AddFile.route) { AddFileScreen(navController = navController) }
        composable(Content.GuideFinishLearning.route) { GuideFinishLearningScreen(navController = navController) }
        composable(Content.Loading.route) { LoadingScreen(navController = navController) }
        composable(Content.Welcome.route) { WelcomeScreen(navController = navController) }
        composable(Content.BuildGroup.route) { BuildGroupScreen(navController = navController) }
    }
}