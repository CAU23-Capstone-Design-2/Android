package kangparks.android.vostom.navigations

import android.content.Context
import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kangparks.android.vostom.R
import kangparks.android.vostom.screens.content.CreateCoverSongScreen
import kangparks.android.vostom.screens.content.DeleteCoverSongScreen
import kangparks.android.vostom.screens.content.DetailLikeCoverItemScreen
import kangparks.android.vostom.screens.content.DetailMyCoverItemScreen
import kangparks.android.vostom.screens.content.DetailMyGroupCoverItemScreen
import kangparks.android.vostom.screens.content.DetailStarCoverItemScreen
import kangparks.android.vostom.screens.content.DetailStarListScreen
import kangparks.android.vostom.screens.group.AddCoverToGroupScreen
import kangparks.android.vostom.screens.group.BuildGroupScreen
import kangparks.android.vostom.screens.group.EditGroupScreen
import kangparks.android.vostom.screens.group.GroupListScreen
import kangparks.android.vostom.screens.group.GroupScreen
import kangparks.android.vostom.screens.group.RemoveCoverFromGroupScreen
import kangparks.android.vostom.screens.home.HomeScreen
import kangparks.android.vostom.screens.profile.EditProfileScreen
import kangparks.android.vostom.screens.profile.ProfileScreen
import kangparks.android.vostom.screens.profile.RequestCoverSongListScreen
import kangparks.android.vostom.utils.store.getAccessToken
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.content.StarContentViewModel
import kangparks.android.vostom.viewModel.group.CurrentGroupViewModel
import kangparks.android.vostom.viewModel.group.GroupInfoVIewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

sealed class HomeContent(val route: String) {
    object Home : HomeContent(route = "home")
    object GroupList : HomeContent(route = "group_list")
    object Profile : HomeContent(route = "profile")
    object BuildGroup : HomeContent(route = "build_group")
    object CreateCoverSong : HomeContent(route = "create_cover_song")
    object DeleteCoverSong : HomeContent(route = "delete_cover_song")
    object RequestCoverSongList : HomeContent(route = "request_cover_song_list")
    object DetailMyCoverItem : HomeContent(route = "detail_my_cover_item")
    object DetailMyGroupCoverItem : HomeContent(route = "detail_my_group_cover_item")
    object DetailStarList : HomeContent(route = "detail_star_list")
    object DetailStarCoverItem : HomeContent(route = "detail_star_cover_item")
    object DetailLikeCoverItem : HomeContent(route = "detail_like_cover_item")
    object Group : HomeContent(route = "group")
    object AddCoverToGroup : HomeContent(route = "add_cover_to_group")
    object RemoveCoverFromGroup : HomeContent(route = "remove_cover_from_group")
    object EditGroup : HomeContent(route = "edit_group")
    object EditProfile : HomeContent(route = "edit_profile")
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
    contentPlayerViewModel: ContentPlayerViewModel,
    contentStoreViewModel: ContentStoreViewModel,
    currentGroupViewModel: CurrentGroupViewModel,
    context : Context
) {

    val accessToken = getAccessToken(context)
    val startContentViewModel = StarContentViewModel()

    Log.d("Test-HomeContentNavigation", "HomeContentNavigation")

    navigation(
        route = Nav.HOME_CONTENT,
        startDestination = HomeContent.Home.route
    ) {
        composable(HomeContent.Home.route) {
            HomeScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                starContentViewModel = startContentViewModel,
                contentPlayerViewModel = contentPlayerViewModel
            )
        }
        composable(HomeContent.GroupList.route) {
            GroupListScreen(
                navController = navController,
                contentPlayerViewModel = contentPlayerViewModel,
                contentStoreViewModel = contentStoreViewModel,
                currentGroupViewModel = currentGroupViewModel,
            )
        }
        composable(HomeContent.Profile.route) {
            ProfileScreen(
                navController = navController,
                contentPlayerViewModel = contentPlayerViewModel,
                contentStoreViewModel = contentStoreViewModel,
            )
        }
        composable(HomeContent.BuildGroup.route) {
            BuildGroupScreen(navController = navController)
        }
        composable(HomeContent.CreateCoverSong.route) {
            CreateCoverSongScreen(
                navController = navController,
//                createContentViewModel = createContentViewModel
            )
        }
        composable(HomeContent.DeleteCoverSong.route) {
            DeleteCoverSongScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel
            )
        }
        composable(HomeContent.RequestCoverSongList.route){
            RequestCoverSongListScreen(
                navController = navController
            )
        }
        composable(HomeContent.DetailMyCoverItem.route){
            DetailMyCoverItemScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                contentPlayerViewModel = contentPlayerViewModel
            )
        }
        composable(HomeContent.DetailMyGroupCoverItem.route){
            DetailMyGroupCoverItemScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                contentPlayerViewModel = contentPlayerViewModel
            )
        }
        composable(HomeContent.DetailStarList.route){
            DetailStarListScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                startContentViewModel = startContentViewModel,
                contentPlayerViewModel = contentPlayerViewModel
            )
        }
        composable(HomeContent.DetailStarCoverItem.route){
            DetailStarCoverItemScreen(
                navController = navController,
                startContentViewModel = startContentViewModel,
                contentPlayerViewModel = contentPlayerViewModel,
                contentStoreViewModel = contentStoreViewModel,
            )
        }
        composable(HomeContent.DetailLikeCoverItem.route){
            DetailLikeCoverItemScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                contentPlayerViewModel = contentPlayerViewModel
            )
        }
        composable(HomeContent.Group.route){
            GroupScreen(
                navController = navController,
                contentPlayerViewModel = contentPlayerViewModel,
                contentStoreViewModel = contentStoreViewModel,
                currentGroupViewModel = currentGroupViewModel,
            )
        }
        composable(HomeContent.AddCoverToGroup.route){
            AddCoverToGroupScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                currentGroupViewModel = currentGroupViewModel,
            )
        }
        composable(HomeContent.RemoveCoverFromGroup.route){
            RemoveCoverFromGroupScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                currentGroupViewModel = currentGroupViewModel,
            )
        }
        composable(HomeContent.EditGroup.route){
            EditGroupScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                currentGroupViewModel = currentGroupViewModel
            )
        }

        composable(HomeContent.EditProfile.route){
            EditProfileScreen(
                navController = navController,
                contentStoreViewModel = contentStoreViewModel,
                userName = contentStoreViewModel.userName.value!!,
                userImgUrl = contentStoreViewModel.userImgUrl.value!!,
            )
        }
    }
}