package kangparks.android.vostom.navigations

import android.content.Context
import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.models.learning.LearningState
import kangparks.android.vostom.screens.error.ErrorScreen
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

sealed class Content(val route: String) {
    object Error : Content(route = "error")
}

fun NavGraphBuilder.contentNavigation(
    navController: NavHostController,
    contentPlayerViewModel: ContentPlayerViewModel,
    contentStoreViewModel : ContentStoreViewModel,
    currentGroupViewModel: CurrentGroupViewModel,
    learningState : LearningState?
) {
    // TODO("로그인 검증 후 사용자 학습 유무 확인 하기")
    val isFinishedLearningUserVoice = if(learningState !=null) if(learningState == LearningState.AfterLearning) true else false else false
    Log.d("Test-ContentNavigation","ContentNavigation")
    Log.d("Test-ContentNavigation", "isFinishedLearningUserVoice : $isFinishedLearningUserVoice")

    navigation(
        route = Nav.CONTENT,
        startDestination =
            if (isFinishedLearningUserVoice) Nav.HOME_CONTENT
            else Nav.LEARNING_CONTENT,
    ) {
        learningContentNavigation(
            navController = navController,
            learningState = learningState
        )
        homeContentNavigation(
            navController = navController,
            contentPlayerViewModel = contentPlayerViewModel,
            contentStoreViewModel = contentStoreViewModel,
            currentGroupViewModel = currentGroupViewModel,
        )
        composable(route = Content.Error.route) {
            ErrorScreen(navController = navController)
        }
    }
}