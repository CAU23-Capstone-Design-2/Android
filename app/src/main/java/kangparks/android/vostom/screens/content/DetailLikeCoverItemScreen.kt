package kangparks.android.vostom.screens.content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kangparks.android.vostom.components.appbar.ContentAppBar
import kangparks.android.vostom.components.item.UserCoverSongItem
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@Composable
fun DetailLikeCoverItemScreen(
    navController: NavHostController,
    contentStoreViewModel: ContentStoreViewModel,
    contentPlayerViewModel : ContentPlayerViewModel
) {
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)

    val myLikeCoverItemList = contentStoreViewModel.likeItemList.observeAsState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme
        )
    }

    BackHandler(enabled = true) {
        if(contentPlayerViewModel.isShowPlayer.value == true){
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isDarkTheme
            )
            contentPlayerViewModel.hidePlayer()
            return@BackHandler
        }
        else{
            navController.popBackStack()
        }
    }

    HomeContentLayoutTemplate(
        contentPlayerViewModel = contentPlayerViewModel,
        navController = navController,
        surfaceBottomPadding = 0,
        playerBottomPadding = 20,
        surfaceModifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        isPlaying = isPlaying
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ){
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "뒤로",
            )
            Text(
                text = "좋아요 한 커버곡",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    bottom = if(isPlaying.value) 90.dp else 48.dp
                )
            ){
                myLikeCoverItemList.value?.let {
                    List(it.size){index ->
                        item {
                            if(index % 2 == 0){
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            end = 10.dp
                                        )
                                ){
                                    UserCoverSongItem(
                                        content = it[index],
                                        contentSize = (screenWidth-60)/2,
                                        onClick = {
                                            contentPlayerViewModel.playMusic(it[index])
                                        }
                                    )
                                }
                            }
                            else{
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 10.dp
                                        )
                                ){
                                    UserCoverSongItem(
                                        content = it[index],
                                        contentSize = (screenWidth-60)/2,
                                        onClick = {
                                            contentPlayerViewModel.playMusic(it[index])
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}