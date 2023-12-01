package kangparks.android.vostom.navigations

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
) {
    // TODO("로그인 검증 후 사용자 학습 유무 확인 하기")
    val isLearnUserVoice = false

    navigation(
        route = Nav.CONTENT,
        startDestination =
            if (isLearnUserVoice) Nav.HOME_CONTENT
            else Nav.LEARNING_CONTENT,
    ) {
        learningContentNavigation(navController = navController)
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