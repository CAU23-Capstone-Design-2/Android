package kangparks.android.vostom.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.screens.error.ErrorScreen
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.learning.LearningStateViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

sealed class Content(val route: String) {
    object Error : Content(route = "error")
}

fun NavGraphBuilder.contentNavigation(
    navController: NavHostController,
    contentPlayerViewModel: ContentPlayerViewModel,
    contentStoreViewModel : ContentStoreViewModel,
    currentGroupViewModel: CurrentGroupViewModel,
    learningStateViewModel : LearningStateViewModel,
    checkRunningService : (serviceClass: Class<*>)-> Boolean
) {

    navigation(
        route = Nav.CONTENT,
        startDestination = Nav.LEARNING_CONTENT
    ) {
        learningContentNavigation(
            navController = navController,
            checkRunningService = checkRunningService,
            learningStateViewModel = learningStateViewModel,
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