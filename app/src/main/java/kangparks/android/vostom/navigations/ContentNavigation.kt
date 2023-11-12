package kangparks.android.vostom.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.screens.error.ErrorScreen

sealed class Content(val route: String) {
    object Error : Content(route = "error")
}

fun NavGraphBuilder.contentNavigation(
    navController: NavHostController,
) {
    // TODO("로그인 검증 후 사용자 학습 유무 확인 하기")
    val isLearnUserVoice = true

    navigation(
        route = Nav.CONTENT,
        startDestination =
            if (isLearnUserVoice) Nav.HOME_CONTENT
            else Nav.LEARNING_CONTENT,
    ) {
        homeContentNavigation(navController = navController)
        learningContentNavigation(navController = navController)
        composable(route = Content.Error.route) {
            ErrorScreen(navController = navController)
        }
    }
}