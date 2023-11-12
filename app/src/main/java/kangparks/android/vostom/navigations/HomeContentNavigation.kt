package kangparks.android.vostom.navigations

import android.content.Context
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.R
import kangparks.android.vostom.screens.group.BuildGroupScreen
import kangparks.android.vostom.screens.group.GroupListScreen
import kangparks.android.vostom.screens.home.HomeScreen
import kangparks.android.vostom.screens.profile.ProfileScreen
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.home.HomeViewModelFactory

sealed class HomeContent(val route: String) {
    object Home : HomeContent(route = "home")
    object GroupList : HomeContent(route = "group_list")
    object Profile : HomeContent(route = "profile")
    object BuildGroup : HomeContent(route = "build_group")

}

sealed class BottomBarContent(
    val route: String,
    val fillIcon: Int,
    val outlineIcon : Int,
    val title: String
) {
    object Home : BottomBarContent(
        route = HomeContent.Home.route,
        fillIcon = R.drawable.home,
        outlineIcon = R.drawable.home_outline,
        title = "홈"
    )

    object GroupList : BottomBarContent(
        route = HomeContent.GroupList.route,
        fillIcon = R.drawable.people,
        outlineIcon = R.drawable.people_outline,
        title = "그룹"
    )

    object Profile : BottomBarContent(
        route = HomeContent.Profile.route,
        fillIcon = R.drawable.person,
        outlineIcon = R.drawable.person_outline,
        title = "프로필"
    )
}
fun NavGraphBuilder.homeContentNavigation(
    navController: NavHostController,
    context: Context
) {
//    val accessToken = getAccessToken(context)
    val accessToken = "access_token"

    navigation(
        route = Nav.HOME_CONTENT,
        startDestination = HomeContent.Home.route
    ) {
        composable(HomeContent.Home.route) {
            HomeScreen(
                navController = navController,
                token = accessToken,
//                homeViewModel = viewModel(
//                    factory = HomeViewModelFactory(accessToken)
//                    )
            )
        }
        composable(HomeContent.GroupList.route) {
            GroupListScreen(
                navController = navController
            )
        }
        composable(HomeContent.Profile.route) { ProfileScreen(navController = navController) }
        composable(HomeContent.BuildGroup.route) { BuildGroupScreen(navController = navController) }
    }
}