package kangparks.android.vostom.navigations

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.models.learning.LearningState
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
import kangparks.android.vostom.viewModel.bottomsheet.CelebrityContentViewModel
import kangparks.android.vostom.viewModel.learning.LearningStateViewModel
import kangparks.android.vostom.viewModel.learning.ScriptProviderViewModel
import kangparks.android.vostom.viewModel.learning.SingingViewModel
import kangparks.android.vostom.viewModel.recorder.RecordFileViewModel

sealed class LearningContent(val route: String) {
    object Guide : LearningContent(route = "guide")
    object DetailGuide : LearningContent(route = "detail_guide")
    object PermissionGuide : LearningContent(route = "permission_guide")
    object GuideScript : LearningContent(route = "guide_script")
    object LearningScript : LearningContent(route = "learning_script")
    object FinishLearningScript : LearningContent(route = "finish_learning_script")
    object GuideSinging : LearningContent(route = "guide_singing")
    object LearningSinging : LearningContent(route = "learning_singing")
    object FinishLearningSinging : LearningContent(route = "finish_learning_singing")
    object AddFile : LearningContent(route = "add_file")
    object GuideFinishLearning : LearningContent(route = "guide_finish_learning")
    object CountDown : LearningContent(route = "count_down")
    object Loading : LearningContent(route = "loading")
    object Welcome : LearningContent(route = "welcome")
}

fun NavGraphBuilder.learningContentNavigation(
    navController: NavHostController,
    checkRunningService : (serviceClass: Class<*>)-> Boolean,
    learningStateViewModel : LearningStateViewModel,
) {
    val singingViewModel = SingingViewModel()
    val recordFileViewModel = RecordFileViewModel()
    val scriptProvider = ScriptProviderViewModel()
    val celebrityContentViewModel = CelebrityContentViewModel()

    navigation(
        route = Nav.LEARNING_CONTENT,
        startDestination = LearningContent.Guide.route
    ) {
        composable(LearningContent.Guide.route) {
            GuideScreen(
                navController = navController,
                celebrityContentViewModel = celebrityContentViewModel
            )
        }
        composable(LearningContent.DetailGuide.route) { DetailGuideScreen(navController = navController) }
        composable(LearningContent.PermissionGuide.route) { PermissionGuideScreen(navController = navController) }
        composable(LearningContent.CountDown.route + "/{destination}") { it ->
            val destination = it.arguments?.getString("destination") ?: Content.Error.route
            CountDownScreen(
                navController = navController,
                destination = destination
            )
        }
        composable(LearningContent.GuideScript.route) {
            GuideScriptScreen(
                navController = navController,
                scriptProvider = scriptProvider
            )
        }
        composable(LearningContent.LearningScript.route) {
            LearningScriptScreen(
                navController = navController,
                recordFileViewModel = recordFileViewModel,
                scriptProvider = scriptProvider
            )
        }
        composable(LearningContent.FinishLearningScript.route) {
            FinishLearningScriptScreen(
                navController = navController,
                scriptProvider = scriptProvider
            )
        }
        composable(LearningContent.GuideSinging.route) {
            GuideSingingScreen(
                navController = navController,
                singingViewModel = singingViewModel
            )
        }
        composable(LearningContent.LearningSinging.route) {
            LearningSingingScreen(
                navController = navController,
                singingViewModel = singingViewModel,
                recordFileViewModel = recordFileViewModel
            )
        }
        composable(LearningContent.FinishLearningSinging.route) { FinishLearningSingingScreen(navController = navController) }
        composable(LearningContent.AddFile.route) {
            AddFileScreen(
                navController = navController,
                recordFileViewModel = recordFileViewModel
            )
        }
        composable(LearningContent.GuideFinishLearning.route) {
            GuideFinishLearningScreen(
                navController = navController,
                recordFileViewModel = recordFileViewModel
            )
        }
        composable(LearningContent.Loading.route) {
            LoadingScreen(
                navController = navController,
                celebrityContentViewModel = celebrityContentViewModel,
                learningStateViewModel = learningStateViewModel,
                checkRunningService = checkRunningService
            )
        }
        composable(LearningContent.Welcome.route) { WelcomeScreen(navController = navController) }
    }
}