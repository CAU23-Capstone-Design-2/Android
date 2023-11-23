package kangparks.android.vostom.screens.content

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import kangparks.android.vostom.components.item.OthersItem
import kangparks.android.vostom.components.template.HomeContentLayoutTemplate
import kangparks.android.vostom.navigations.HomeContent
import kangparks.android.vostom.viewModel.content.ContentStoreViewModel
import kangparks.android.vostom.viewModel.content.StarContentViewModel
import kangparks.android.vostom.viewModel.player.ContentPlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailStarListScreen(
    navController: NavHostController,
    token : String,
    contentStoreViewModel: ContentStoreViewModel,
    startContentViewModel: StarContentViewModel,
    contentPlayerViewModel : ContentPlayerViewModel
) {
    val othersItemList = contentStoreViewModel.othersItemList.observeAsState()
    val isPlaying = contentPlayerViewModel.isPlaying.observeAsState(initial = false)

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val isDarkTheme = isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()

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
//                        .windowInsetsPadding(WindowInsets.statusBars)
//                        .padding(bottom = 48.dp)
                .padding(horizontal = 20.dp)
        ) {
            ContentAppBar(
                backButtonAction = {
                    navController.popBackStack()
                },
                backButtonContent = "뒤로",
            )
            Text(
                text = "연예인 커버곡",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    bottom = if(isPlaying.value) 90.dp else 48.dp
                )
            ) {
                othersItemList.value?.let {
                    List(it.size) { index ->
                        item {
                            if (index % 3 == 0) {
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            end = 5.dp
                                        )
                                ) {
                                    OthersItem(
                                        content = it[index],
                                        contentSize = (screenWidth - 60) / 3,
                                        onClick = {
                                            startContentViewModel.updateCurrentSinger(
                                                accessToken = token,
                                                singer = it[index]
                                            )
                                            navController.navigate(HomeContent.DetailStarCoverItem.route)
                                        }
                                    )
                                }
                            } else if (index % 3 == 1) {
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 5.dp,
                                            end = 5.dp
                                        )
                                ) {
                                    OthersItem(
                                        content = it[index],
                                        contentSize = (screenWidth - 60) / 3,
                                        onClick = {
                                            startContentViewModel.updateCurrentSinger(
                                                accessToken = token,
                                                singer = it[index]
                                            )
                                            navController.navigate(HomeContent.DetailStarCoverItem.route)
                                        }
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            top = 10.dp,
                                            bottom = 10.dp,
                                            start = 5.dp
                                        )
                                ) {
                                    OthersItem(
                                        content = it[index],
                                        contentSize = (screenWidth - 60) / 3,
                                        onClick = {
                                            startContentViewModel.updateCurrentSinger(
                                                accessToken = token,
                                                singer = it[index]
                                            )
                                            navController.navigate(HomeContent.DetailStarCoverItem.route)
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
//    Scaffold(
////        contentWindowInsets =
//    ){
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .windowInsetsPadding(WindowInsets.statusBars)
//                .navigationBarsPadding()
////                .padding(bottom = 40.dp)
//        ){
//            Box{
//
//                AnimatedVisibility(
//                    visible = isPlaying.value,
//                    enter = fadeIn(),
//                    exit = fadeOut()
//                ) {
//                    BottomContentPlayer(
//                        navController = navController,
//                        contentPlayerViewModel = contentPlayerViewModel,
//                        bottomPaddingValue = 20
//                    )
//                }
//            }
//        }
//    }

}